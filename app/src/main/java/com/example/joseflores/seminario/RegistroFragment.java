package com.example.joseflores.seminario;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment implements View.OnClickListener{

    private TextView txt_fecha;

    private EditText edi_codigo;
    private EditText edi_expositor;
    private EditText edi_evaluador;
    private EditText edi_asesor;

    private Spinner maestria;
    private Spinner doctorado;

    private String maestriaSeleccionada;
    private String doctoradoSeleccionado;

    private Button btnGuardar;

    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        edi_codigo = (EditText) view.findViewById(R.id.edi_codigo);
        edi_expositor = (EditText) view.findViewById(R.id.editExpositor);
        edi_evaluador = (EditText) view.findViewById(R.id.editEvaluador);
        edi_asesor = (EditText) view.findViewById(R.id.editAsesor);

        maestria = (Spinner) view.findViewById(R.id.spiMaestria);
        doctorado = (Spinner) view.findViewById(R.id.spiDoctorado);

        txt_fecha = (TextView) view.findViewById(R.id.txt_fecha);

        btnGuardar = (Button) view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        fechaActual();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnGuardar: registrarExpositor(); break;

        }

    }

    private void registrarExpositor() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext(), DDL.BASE_DE_DATOS, null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        maestriaSeleccionada = maestria.getSelectedItem().toString();
        doctoradoSeleccionado = doctorado.getSelectedItem().toString();

        try {

            ContentValues values = new ContentValues();
            values.put(DDL.ID_EXPOSITOR, edi_codigo.getText().toString());
            values.put(DDL.NOMBRE_EXPOSITOR, edi_expositor.getText().toString());
            values.put(DDL.NOMBRE_EVALUADOR, edi_evaluador.getText().toString());
            values.put(DDL.NOMBRE_ASESOR, edi_asesor.getText().toString());
            values.put(DDL.MAESTRIA, maestriaSeleccionada);
            values.put(DDL.DOCTORADO, doctoradoSeleccionado);
            values.put(DDL.FECHA, txt_fecha.getText().toString());

            Long id =  db.insert(DDL.TABLA_EXPOSITOR, DDL.ID_EXPOSITOR, values);


            Toast.makeText(getContext(), "Expositor "+ id +" guardado", Toast.LENGTH_SHORT).show();

            btnGuardar.setEnabled(false);

            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();

            db.close();

        }

    }

    private void fechaActual(){

        SimpleDateFormat fechaActual = new SimpleDateFormat("dd-MM-yyyy");

        Calendar calendar = Calendar.getInstance();

        String fecha = fechaActual.format(calendar.getTime());

        txt_fecha.setText(fecha);
    }
}
