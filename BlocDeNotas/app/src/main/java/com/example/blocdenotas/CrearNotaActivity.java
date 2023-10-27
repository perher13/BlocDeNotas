package com.example.blocdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearNotaActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button boton1;
    protected Button boton2;
    protected EditText caja1;

    private String contenidoCaja1;

    protected DataBaseSQL db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notitas_principal);

        label1 = (TextView) findViewById(R.id.textView);
        boton1 = (Button) findViewById(R.id.button_notas);
        boton2 = (Button) findViewById(R.id.button_notas2);
        caja1 = (EditText) findViewById(R.id.editTextTextPersonName);

        db = new DataBaseSQL(this);

        /*System.out.println("-->Numero de notas: " + db.numberOfNotes());
        db.insertNote("-->Bajar al perro", 1);
        System.out.println("-->Numero de notas: " + db.numberOfNotes());

        db.getAllNotes();
        //db.deleteAllNotes();

        db.close();*/

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenidoCaja1 = caja1.getText().toString();
                if(contenidoCaja1.equals(""))
                {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crearNotas), Toast.LENGTH_SHORT).show();
                }else{
                    //Insertar en base de datos
                    db.insertNote(caja1.getText().toString(), 1);
                    db.getAllNotes();

                    Intent pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crearNotas), Toast.LENGTH_SHORT).show();
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

    }
}