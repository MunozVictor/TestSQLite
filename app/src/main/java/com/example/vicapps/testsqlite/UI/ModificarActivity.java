package com.example.vicapps.testsqlite.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vicapps.testsqlite.DB.ContactDatasource;
import com.example.vicapps.testsqlite.Model.Contacto;
import com.example.vicapps.testsqlite.R;

public class ModificarActivity extends AppCompatActivity {
    private EditText etId,etNombre,etEmail;
    private Button btnCancel,btnModificar;
    private Contacto contacto;
    private ContactDatasource db = new ContactDatasource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Intent i = getIntent();
        etId = (EditText) findViewById(R.id.etIdMod);
        etNombre= (EditText) findViewById(R.id.etNombreMod);
        etEmail= (EditText) findViewById(R.id.etEmailMod);
        btnCancel = (Button) findViewById(R.id.btnCancelar);
        btnModificar= (Button) findViewById(R.id.btnModificar);


        etId.setText(i.getIntExtra(MostrarActivity.ID,0)+"");
        etNombre.setText(i.getStringExtra(MostrarActivity.NOMBRE));
        etEmail.setText(i.getStringExtra(MostrarActivity.EMAIL));


        etId.setEnabled(false);
    }

    public void verLista(){
        Intent i = new Intent(this,MostrarActivity.class);
        finish();
        startActivity(i);
    }
    public void cancelar (View v){
        verLista();
    }
    public  void modificar(View v){
        contacto = new Contacto(Integer.parseInt(etId.getText().toString()),etNombre.getText().toString(),etEmail.getText().toString());
        db.actualizarContacto(contacto);
        verLista();
    }
}
