package com.example.blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerNotaActivity extends AppCompatActivity {

    protected TextView text1;
    protected TextView text2;
    protected Button boton1;
    protected Button boton2;

    private Bundle extras;
    private String paquete1= "";
    private int paquete2;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        text1=(TextView) findViewById(R.id.textView3);
        text2=(TextView) findViewById(R.id.textView4);

        boton1=(Button) findViewById(R.id.buttonvolver_ver);
        boton2=(Button) findViewById(R.id.buttonborrar_ver);

        db = new DataBaseSQL(this);

        extras= getIntent().getExtras();
        if(extras != null){
            paquete1=extras.getString("NOTA");
            paquete2=extras.getInt("ID");

            text2.setText(paquete1);

            boton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            });

            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    db.deleteNote(paquete2);
                    System.out.println(paquete2);
                    db.getAllNotes();

                    Toast.makeText(VerNotaActivity.this, getString(R.string.toast1_VerNotas), Toast.LENGTH_SHORT).show();

                    Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            });
        }

    }
}