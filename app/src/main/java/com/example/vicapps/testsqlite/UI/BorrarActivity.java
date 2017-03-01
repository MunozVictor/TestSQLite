package com.example.vicapps.testsqlite.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vicapps.testsqlite.R;

public class BorrarActivity extends AppCompatActivity {
    EditText etIdBorrar;
    Button btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        etIdBorrar = (EditText) findViewById(R.id.etIdBorrar);
        btnBorrar = (Button) findViewById(R.id.btnBorrarId);
    }
}
