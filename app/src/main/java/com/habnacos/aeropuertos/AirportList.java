package com.habnacos.aeropuertos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.habnacos.aeropuertos.models.Airport;
import com.habnacos.aeropuertos.models.AirportController;
import com.habnacos.aeropuertos.models.DB;

import java.util.ArrayList;

public class AirportList extends AppCompatActivity {
    ArrayList<Airport> airports = new ArrayList<>();
    RecyclerView listViewAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_list);

        listViewAirport = findViewById(R.id.listViewAirport);
        listViewAirport.setLayoutManager(new LinearLayoutManager(this));

        AirportController controller = new AirportController(getApplicationContext());
        Cursor cursor = controller.getAll();
        if (cursor.getCount() <= 0) {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            cursor.moveToFirst();
            do {
                airports.add(new Airport(
                   cursor.getString(cursor.getColumnIndex(DB.col_code)),
                   cursor.getString(cursor.getColumnIndex(DB.col_name)),
                   cursor.getString(cursor.getColumnIndex(DB.col_country)),
                   cursor.getString(cursor.getColumnIndex(DB.col_city)),
                   cursor.getString(cursor.getColumnIndex(DB.col_address)),
                   cursor.getString(cursor.getColumnIndex(DB.col_latitude)),
                   cursor.getString(cursor.getColumnIndex(DB.col_length))
                ));
            } while(cursor.moveToNext());

            AirportAdapter adapter = new AirportAdapter(getApplicationContext(), airports, listViewAirport, this);
        }
    }
}