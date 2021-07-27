package com.habnacos.aeropuertos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.habnacos.aeropuertos.models.Airport;
import com.habnacos.aeropuertos.models.AirportController;
import com.habnacos.aeropuertos.models.DB;

public class AirportDetails extends AppCompatActivity {
    TextView d_code, d_name, d_country, d_city, d_address, d_latitude, d_length;
    Button blBack, blDelete, blSave;
    String code;
    AirportController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);

        d_code     = findViewById(R.id.d_code);
        d_name     = findViewById(R.id.d_name);
        d_country  = findViewById(R.id.d_country);
        d_city     = findViewById(R.id.d_city);
        d_address  = findViewById(R.id.d_address);
        d_latitude = findViewById(R.id.d_latitude);
        d_length   = findViewById(R.id.d_length);
        blBack     = findViewById(R.id.blBack);
        blDelete   = findViewById(R.id.blDelete);
        blSave     = findViewById(R.id.blSave);

        code = getIntent().getStringExtra("code");
        controller = new AirportController(getApplicationContext());
        Cursor cursor = controller.getByCode(code);

        Airport airport = new Airport(
                cursor.getString(cursor.getColumnIndex(DB.col_code)),
                cursor.getString(cursor.getColumnIndex(DB.col_name)),
                cursor.getString(cursor.getColumnIndex(DB.col_country)),
                cursor.getString(cursor.getColumnIndex(DB.col_city)),
                cursor.getString(cursor.getColumnIndex(DB.col_address)),
                cursor.getString(cursor.getColumnIndex(DB.col_latitude)),
                cursor.getString(cursor.getColumnIndex(DB.col_length))
        );

        d_code.setText(airport.getCode());
        d_name.setText(airport.getName());
        d_country.setText(airport.getCountry());
        d_city.setText(airport.getCity());
        d_address.setText(airport.getAddress());
        d_latitude.setText(airport.getLatitude());
        d_length.setText(airport.getLength());

        blBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        blDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controller.deleteByCode(code)) {
                    Toast.makeText(AirportDetails.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AirportDetails.this, MainActivity.class));
                } else
                    Toast.makeText(AirportDetails.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
            }
        });
        blSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code, name, country, city, address, latitude, length;
                code     = d_code.getText().toString();
                name     = d_name.getText().toString();
                country  = d_country.getText().toString();
                city     = d_city.getText().toString();
                address  = d_address.getText().toString();
                latitude = d_latitude.getText().toString();
                length   = d_length.getText().toString();

                if (code.isEmpty())
                    Toast.makeText(AirportDetails.this, "Código requerido", Toast.LENGTH_SHORT).show();
                else if (name.isEmpty())
                    Toast.makeText(AirportDetails.this, "Nombre requerido", Toast.LENGTH_SHORT).show();
                else if (country.isEmpty())
                    Toast.makeText(AirportDetails.this, "País requerido", Toast.LENGTH_SHORT).show();
                else if (city.isEmpty())
                    Toast.makeText(AirportDetails.this, "Ciudad requerido", Toast.LENGTH_SHORT).show();
                else if (address.isEmpty())
                    Toast.makeText(AirportDetails.this, "Dirección requerido", Toast.LENGTH_SHORT).show();
                else if (latitude.isEmpty())
                    Toast.makeText(AirportDetails.this, "Latitud requerido", Toast.LENGTH_SHORT).show();
                else if (length.isEmpty())
                    Toast.makeText(AirportDetails.this, "Longitud requerido", Toast.LENGTH_SHORT).show();
                else {
                    Airport air = new Airport(code, name, country, city, address, latitude, length);
                    if (controller.updateByCode(code, air)) {
                        Toast.makeText(AirportDetails.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(AirportDetails.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}