package com.example.blocdenotas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public DataBaseSQL(Context context){
        super(context, "notas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement not null, title text, priority int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notas");
    }

    public void insertNote(String title, int priority)
    {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notas(title, priority) VALUES ('"+title+"',"+priority+")");
    }

    public int numberOfNotes()
    {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db,"notas");
        return num;
    }

    public void deleteAllNotes()
    {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void deleteNote(int id){
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id=" + id);
    }

    public void updateNotes(int id, String title, int priority){
        db = this.getWritableDatabase();
        db.execSQL("UPDATE notas SET title='"+title+"',priority="+priority+" notas WHERE id="+id);
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNotes(){
        ArrayList<String> filas = new ArrayList<String>();

        Cursor res=null;
        String contenido="";
        Integer id;
        if(numberOfNotes()>0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas ORDER BY priority ASC", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
                id= res.getInt(res.getColumnIndex("id"));
                filas.add(contenido);
                System.out.println("ID: "+ id);
                //System.out.println("-->" + contenido);
                res.moveToNext();
            }
        }
        return filas;
    }

    public void close()
    {
        db.close();
    }
}
