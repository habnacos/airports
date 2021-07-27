package com.habnacos.aeropuertos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.habnacos.aeropuertos.models.Airport;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder> {
    ArrayList<Airport> airports;
    RecyclerView recyclerView;
    Context context;
    AirportList airportList;

    public AirportAdapter(Context context, ArrayList<Airport> airports, RecyclerView recyclerView, AirportList airportList) {
        this.context = context;
        this.airports = airports;
        this.airportList = airportList;
        this.recyclerView = recyclerView;
        this.recyclerView.setAdapter(this);
    }

    @Override
    public AirportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_airport_list, null, false);
        return new AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AirportViewHolder holder, int position) {
        Airport airport = airports.get(position);
        holder.code = airport.getCode();
        holder.tName.setText("Nombre: " + airport.getName());
        holder.tCode.setText("Código: " + airport.getCode());
        holder.tCountryCity.setText("País: " + airport.getCountry()+" - Ciudad: " + airport.getCity());
        holder.tAddress.setText("Dirección: " + airport.getAddress());
        holder.tLatitude.setText("Latitud: " + airport.getLatitude());
        holder.tLength.setText("Longitud: " + airport.getLength());
    }

    @Override
    public int getItemCount() {
        return this.airports.size();
    }

    public class AirportViewHolder extends RecyclerView.ViewHolder {
        String code;
        TextView tName, tCode, tCountryCity, tAddress, tLatitude, tLength;
        Button bView;

        public AirportViewHolder(final View itemView) {
            super(itemView);

            tName        = itemView.findViewById(R.id.tName);
            tCode        = itemView.findViewById(R.id.tCode);
            tCountryCity = itemView.findViewById(R.id.tCountryCity);
            tAddress     = itemView.findViewById(R.id.tAddress);
            tLatitude    = itemView.findViewById(R.id.tLatitude);
            tLength      = itemView.findViewById(R.id.tLength);
            bView        = itemView.findViewById(R.id.bView);

            bView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), AirportDetails.class);
                    i.putExtra("code", code);
                    airportList.startActivity(i);
                }
            });
        }
    }
}
