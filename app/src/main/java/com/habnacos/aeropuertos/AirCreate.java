package com.habnacos.aeropuertos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.habnacos.aeropuertos.models.Airport;
import com.habnacos.aeropuertos.models.AirportController;

public class AirCreate extends AppCompatActivity {
    Button btn_c_back, btn_c_save;
    TextView txt_code, txt_name, txt_country, txt_city, txt_address, txt_latitude, txt_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_create);

        btn_c_back = findViewById(R.id.blBack);
        btn_c_save = findViewById(R.id.blSave);

        txt_code     = findViewById(R.id.d_code);
        txt_name     = findViewById(R.id.d_name);
        txt_country  = findViewById(R.id.txt_country);
        txt_city     = findViewById(R.id.txt_city);
        txt_address  = findViewById(R.id.txt_address);
        txt_latitude = findViewById(R.id.txt_latitude);
        txt_length   = findViewById(R.id.txt_length);

        btn_c_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_c_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code, name, country, city, address, latitude, length;
                code     = txt_code.getText().toString();
                name     = txt_name.getText().toString();
                country  = txt_country.getText().toString();
                city     = txt_city.getText().toString();
                address  = txt_address.getText().toString();
                latitude = txt_latitude.getText().toString();
                length   = txt_length.getText().toString();

                if (code.isEmpty())
                    Toast.makeText(AirCreate.this, "Código requerido", Toast.LENGTH_SHORT).show();
                else if (name.isEmpty())
                    Toast.makeText(AirCreate.this, "Nombre requerido", Toast.LENGTH_SHORT).show();
                else if (country.isEmpty())
                    Toast.makeText(AirCreate.this, "País requerido", Toast.LENGTH_SHORT).show();
                else if (city.isEmpty())
                    Toast.makeText(AirCreate.this, "Ciudad requerido", Toast.LENGTH_SHORT).show();
                else if (address.isEmpty())
                    Toast.makeText(AirCreate.this, "Dirección requerido", Toast.LENGTH_SHORT).show();
                else if (latitude.isEmpty())
                    Toast.makeText(AirCreate.this, "Latitud requerido", Toast.LENGTH_SHORT).show();
                else if (length.isEmpty())
                    Toast.makeText(AirCreate.this, "Longitud requerido", Toast.LENGTH_SHORT).show();
                else {
                    AirportController controller = new AirportController(getApplicationContext());
                    Airport airport = new Airport(code, name, country, city, address, latitude, length);

                    if (controller.getByCode(airport.getCode()).getCount() > 0)
                        Toast.makeText(AirCreate.this, "Código ya existente", Toast.LENGTH_SHORT).show();
                    else if (controller.create(airport) > 0) {
                        Toast.makeText(AirCreate.this, "Registro creado", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            }
        });
    }
}