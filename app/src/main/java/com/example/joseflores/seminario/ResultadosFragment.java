package com.example.joseflores.seminario;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosFragment extends Fragment {

    private TextView txt_puntaje;

    private RatingBar ra_puntaje;

    ConexionSQLiteHelper conexion;


    public ResultadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);

        txt_puntaje = (TextView) view.findViewById(R.id.txt_resul_pun);

        ra_puntaje = (RatingBar) view.findViewById(R.id.rat_puntaje);

        obtenerPuntajeTotal();

        return view;
    }

    private void obtenerPuntajeTotal() {

        conexion = new ConexionSQLiteHelper(getContext(), DDL.BASE_DE_DATOS, null, 1);

        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT SUM(" + DDL.PUN_PRE + ") FROM " + DDL.TABLA_PRESENTACION, null);

        Cursor cursor1 = db.rawQuery("SELECT SUM(" + DDL.PUN_ESTILO + ") FROM " + DDL.TABLA_ESTILO, null);

        int pun1 = 0, pun2 = 0;

        while (cursor.moveToNext()){

            pun1 = cursor.getInt(0);

        }

        while (cursor1.moveToNext()){

            pun2 = cursor1.getInt(0);

        }

        txt_puntaje.setText(String.valueOf(pun1 + pun2));

        ra_puntaje.setRating( (float) ((pun1 + pun2) * 6) / 100 );

    }

}
