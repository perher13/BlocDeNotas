package com.example.blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BorrarNotasActivity extends AppCompatActivity {

    protected Button boton1;
    protected Button boton2;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);

        boton1 = (Button) findViewById(R.id.button_borrar);
        boton2 = (Button) findViewById(R.id.buttonvolver);

        db = new DataBaseSQL(this);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllNotes();
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast1_borrarNotas), Toast.LENGTH_SHORT).show();

                Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

    }


}