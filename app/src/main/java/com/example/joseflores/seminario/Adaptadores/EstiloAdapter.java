package com.example.joseflores.seminario.Adaptadores;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.joseflores.seminario.ConexionSQLiteHelper;
import com.example.joseflores.seminario.DDL;
import com.example.joseflores.seminario.Entidades.Criterio;
import com.example.joseflores.seminario.R;

import java.util.ArrayList;

public class EstiloAdapter extends RecyclerView.Adapter<EstiloAdapter.EstiloViewHolder>{

    private ArrayList<Criterio> listCriterios;

    ConexionSQLiteHelper conexionBD;

    public EstiloAdapter(ArrayList<Criterio> listCriterios) {
        this.listCriterios = listCriterios;
    }


    @NonNull
    @Override
    public EstiloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.criterio_item, null, false);

        return new EstiloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EstiloViewHolder holder, final int position) {

        holder.criterio.setText(listCriterios.get(position).getDescripcion());

        holder.puntaje.setText(String.valueOf(listCriterios.get(position).getPuntaje()));

        holder.anadirPuntaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asignarPuntaje(v, listCriterios.get(position).getDescripcion());

            }
        });

    }

    private void asignarPuntaje(final View v, final String descripcion) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());

        dialog.setTitle("Asignar puntaje")
                .setSingleChoiceItems(R.array.puntaje_estilo, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int puntaje_seleccionado = ((AlertDialog)dialog).getListView().getCheckedItemPosition();

                        actualizarPuntaje(v, descripcion, puntaje_seleccionado);

                    }
                })

                .show();

    }

    private void actualizarPuntaje(View v, String descripcion, int puntaje_seleccionado) {

        conexionBD = new ConexionSQLiteHelper(v.getContext(), DDL.BASE_DE_DATOS, null, 1);

        SQLiteDatabase db = conexionBD.getWritableDatabase();

        String[] parametros = {descripcion};

        ContentValues values = new ContentValues();
        values.put(DDL.PUN_ESTILO, puntaje_seleccionado);

        db.update(DDL.TABLA_ESTILO, values, DDL.DESC_ESTILO+"=?",parametros);

        db.close();

    }

    @Override
    public int getItemCount() {
        return this.listCriterios.size();
    }

    public class EstiloViewHolder extends RecyclerView.ViewHolder{

        TextView criterio;
        TextView puntaje;

        Button anadirPuntaje;

        public EstiloViewHolder(View itemView) {
            super(itemView);

            criterio = (TextView) itemView.findViewById(R.id.textDesc);
            puntaje = (TextView) itemView.findViewById(R.id.textPuntaje);

            anadirPuntaje = (Button) itemView.findViewById(R.id.btnAdd);

        }

    }

}
