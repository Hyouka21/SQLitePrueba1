package com.example.sqliteprueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.sqliteprueba1.SqlLite.MySQLiteHelper;

public class MainActivity extends AppCompatActivity {
    private static final String DB_NAME = "comments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO comments (user, comment) VALUES ('Diegito','Esto es un comentario insertado usando el método execSQL() en sqlite')");
            ContentValues cv = new ContentValues();
            cv.put("user", "Gaston");
            cv.put("comment", "Esto es un comentario insertado usando el método insert() en sqlite");
            db.insert("comments", null, cv);
            Cursor c = db.query(
                    DB_NAME,  // Nombre de la tabla
                    null,  // Lista de Columnas a consultar
                    null,  // Columnas para la cláusula WHERE
                    null,  // Valores a comparar con las columnas del WHERE
                    null,  // Agrupar con GROUP BY
                    null,  // Condición HAVING para GROUP BY
                    null  // Cláusula ORDER BY
            );
            while(c.moveToNext()){
                String name = c.getString(1);
                String comentario = c.getString(2);
                Log.d("prueba" ,"datos : user: "+name+" comentario: "+comentario+"\n");
            }

        }
    }
}