package com.example.blocdenotas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    protected ListView list1;
    protected DataBaseSQL db;
    protected ArrayList<String> notasList  = new ArrayList <String>();
    private ArrayAdapter<String> adaptador;

    private String contenidoItem="";
    private String notaContenido="";
    private Integer positionInt;
    private char IDCoger;
    private String IDCoger2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_general);

        db= new DataBaseSQL(this);

        //label1= (TextView) findViewById(R.id.textView);
        list1= (ListView) findViewById(R.id.list1_start);

       /*for (int i = 0; i< db.numberOfNotes(); i++){
           db.getAllNotes();

           notasList.add(i,notaContenido);
       }*/
        /*
        db.insertNote("Bajar al perro", 1);
        db.insertNote("Ordenar los libros", 1);
        db.insertNote("Formatear el ordenador", 1);
        db.insertNote("Comprar el pan", 1);*/


        notasList = db.getAllNotes();
        adaptador=new ArrayAdapter<>(ListadoActivity.this, android.R.layout.simple_list_item_1, notasList);
        list1.setAdapter(adaptador);



        adaptador = new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_list_item_1, notasList);

        list1.setAdapter(adaptador);

        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                contenidoItem= parent.getItemAtPosition(position).toString();
                //Toast.makeText(ListadoActivity.this, "El valor es: " + contenidoItem, Toast.LENGTH_SHORT).show();
                adaptador.notifyDataSetChanged();
                return true;
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //AlertDialog.Builder builder= new AlertDialog.Builder(ListadoActivity.this);
                Toast.makeText(ListadoActivity.this, getString(R.string.toast1_Listado), Toast.LENGTH_SHORT).show();
                contenidoItem= parent.getItemAtPosition(position).toString();
                System.out.println(contenidoItem);
                System.out.println();

                //Toast.makeText(ListadoActivity.this, "El valor es: " + contenidoItem, Toast.LENGTH_SHORT).show();
                adaptador.notifyDataSetChanged();
                IDCoger2=contenidoItem.substring(0, 2);
                positionInt = Integer.parseInt(IDCoger2);

                Intent pasarPantalla= new Intent(ListadoActivity.this, VerNotaActivity.class);
                pasarPantalla.putExtra("NOTA", contenidoItem);
                pasarPantalla.putExtra("ID", positionInt);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_crear:

                Intent pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                finish();
                startActivity(pasarPantalla);

                return true;
            case R.id.item_opciones:

                Intent pasarPantalla2 = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
                finish();
                startActivity(pasarPantalla2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}