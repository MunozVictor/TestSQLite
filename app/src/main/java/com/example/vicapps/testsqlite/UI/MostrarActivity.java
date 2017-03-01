package com.example.vicapps.testsqlite.UI;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vicapps.testsqlite.DB.ContactDatasource;
import com.example.vicapps.testsqlite.Model.Contacto;
import com.example.vicapps.testsqlite.R;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    ListView lista;
    ContactDatasource db = new ContactDatasource(this);
    ArrayList<Contacto> contactos = new ArrayList<Contacto>();
    ArrayList<String> sContactos = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int idCaux;
    final static String NOMBRE = "NOMBRE";
    final static String EMAIL = "EMAIL";
    final static String ID ="ID" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista= (ListView) findViewById(R.id.listView);
        refresh();
        lista.setOnItemClickListener(this);
        lista.setAdapter(adapter);


    }

    private void refresh() {
        contactos = db.readContacts();
        String sc;
        for (Contacto c : contactos){
            sc = c.getId()+" "+c.getName()+" "+c.getEmail();
            sContactos.add(sc);
        }
        adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, sContactos);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
        idCaux = contactos.get(pos).getId();
        Dialog d =  new AlertDialog.Builder(this)
                .setMessage("¿Desea borrar a "+contactos.get(pos).getName()+"?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.borrarContact(idCaux);
                        String cS=sContactos.get(pos);
                        adapter.remove(cS);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNeutralButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(getBaseContext(),ModificarActivity.class);
                        in.putExtra(ID,contactos.get(pos).getId());
                        in.putExtra(NOMBRE,contactos.get(pos).getName());
                        in.putExtra(EMAIL,contactos.get(pos).getEmail());
                        finish();
                        startActivity(in);


                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
