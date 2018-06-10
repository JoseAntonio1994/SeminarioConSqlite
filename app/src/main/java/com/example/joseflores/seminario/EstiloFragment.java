package com.example.joseflores.seminario;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joseflores.seminario.Adaptadores.CriterioAdapter;
import com.example.joseflores.seminario.Adaptadores.EstiloAdapter;
import com.example.joseflores.seminario.Entidades.Criterio;
import com.example.joseflores.seminario.Entidades.Expositor;

import java.util.ArrayList;


public class EstiloFragment extends Fragment {

    private ArrayList<Criterio> listCriterio;

    private EditText ediCriterio;

    private RecyclerView recycler_estilo;

    ConexionSQLiteHelper conexion;

    public EstiloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_superior, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estilo, container, false);

        conexion = new ConexionSQLiteHelper(getContext(), DDL.BASE_DE_DATOS, null, 1);

        listCriterio = new ArrayList<>();

        recycler_estilo = (RecyclerView) view.findViewById(R.id.recycler_estilo);
        recycler_estilo.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarCriterios();

        EstiloAdapter adapter = new EstiloAdapter(listCriterio);
        recycler_estilo.setAdapter(adapter);

        return view;
    }

    private void mostrarCriterios() {

        SQLiteDatabase db = conexion.getReadableDatabase();

        Criterio criterio = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + DDL.TABLA_ESTILO, null);

        while (cursor.moveToNext()){

            criterio = new Criterio();
            criterio.setDescripcion(cursor.getString(0));
            criterio.setPuntaje(cursor.getInt(1));

            listCriterio.add(criterio);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_eye: mostrarExpositor().show(); break;

            case R.id.nav_add: nuevoCriterio().show(); break;

            default: return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog nuevoCriterio() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = this.getLayoutInflater();

        final View view = inflater.inflate(R.layout.nuevo_criterio, null);

        builder.setView(view)

                .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ediCriterio = (EditText) view.findViewById(R.id.edi_nombreCriterio);

                        crearCriterio();

                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.show();

    }

    private void crearCriterio() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext(), DDL.BASE_DE_DATOS, null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(DDL.DESC_ESTILO, ediCriterio.getText().toString());
            values.put(DDL.PUN_ESTILO, 0);

            Long id = db.insert(DDL.TABLA_ESTILO, DDL.DESC_ESTILO, values);

            Toast.makeText(getContext(), "Criterio " + id + " guardado", Toast.LENGTH_SHORT).show();

            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();

            Log.d("ESTADO", e.getMessage());

            db.close();

        }

    }

    private AlertDialog mostrarExpositor() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = this.getLayoutInflater();

        View view = inflater.inflate(R.layout.detalle_expositor, null);

        EditText detalle_expositor;
        EditText detalle_evaluador;
        EditText detalle_asesor;

        detalle_expositor = (EditText) view.findViewById(R.id.edi_detalle_expositor);
        detalle_evaluador = (EditText) view.findViewById(R.id.edi_detalle_evaluador);
        detalle_asesor = (EditText) view.findViewById(R.id.edi_detalle_asesor);

        SQLiteDatabase db = conexion.getReadableDatabase();

        Expositor expositor = null;

        Cursor cursor = db.rawQuery("SELECT " + DDL.NOMBRE_EXPOSITOR + "," + DDL.NOMBRE_EVALUADOR + "," + DDL.NOMBRE_ASESOR
                + " FROM " + DDL.TABLA_EXPOSITOR,null);

        while (cursor.moveToNext()){

            detalle_expositor.setText(cursor.getString(0));
            detalle_evaluador.setText(cursor.getString(1));
            detalle_asesor.setText(cursor.getString(2));

        }

        builder.setView(view)

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();

    }


}
