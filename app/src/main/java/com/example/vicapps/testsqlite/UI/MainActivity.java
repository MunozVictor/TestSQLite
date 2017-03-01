package com.example.vicapps.testsqlite.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vicapps.testsqlite.R;

public class MainActivity extends AppCompatActivity {
    Button btnInsert , btnBorrar , btnModificar , btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBorrar = (Button) findViewById(R.id.btnBorrarId);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);


    }

    public void launchBorrar(View v){
        Intent i = new Intent(this,BorrarActivity.class);
        startActivity(i);

    }
    public void launchInsert(View v){
        Intent i = new Intent(this,InsertActivity.class);
        startActivity(i);

    }
    public void launchModify(View v){
        Intent i = new Intent(this,ModificarActivity.class);
        startActivity(i);

    }
    public void launchMostrar(View v){
        Intent i = new Intent(this,MostrarActivity.class);
        startActivity(i);

    }


}
