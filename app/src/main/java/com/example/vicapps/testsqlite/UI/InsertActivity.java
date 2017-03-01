package com.example.vicapps.testsqlite.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vicapps.testsqlite.DB.ContactDatasource;
import com.example.vicapps.testsqlite.DB.SQLHelper;
import com.example.vicapps.testsqlite.Model.Contacto;
import com.example.vicapps.testsqlite.R;

public class InsertActivity extends AppCompatActivity {
    Button btnInsert;
    EditText etNombre,etEmail;
    ContactDatasource db = new ContactDatasource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        etNombre= (EditText) findViewById(R.id.etNombre);
        etEmail= (EditText) findViewById(R.id.etEmail);

    }
    public void insert(View v){
        if(etNombre.getText().equals("")||etEmail.getText().equals("")){
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            Contacto contacto = new Contacto(-1,etNombre.getText().toString(),etEmail.getText().toString());
            db.insertContact(contacto);
            Toast.makeText(this,"Contacto guardado: "+contacto.getName()+" "+contacto.getEmail(),Toast.LENGTH_SHORT).show();
            limpiar();

        }
    }
    public void limpiar(){
        etNombre.setText("");
        etEmail.setText("");
    }
}
