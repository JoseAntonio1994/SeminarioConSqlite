package com.example.joseflores.seminario;

public class DDL {

    public static final String BASE_DE_DATOS = "seminario";

    public static final String TABLA_EXPOSITOR = "expositor";
    public static final String ID_EXPOSITOR = "idExpositor";
    public static final String NOMBRE_EXPOSITOR = "nomExpositor";
    public static final String NOMBRE_EVALUADOR = "nomEvaluador";
    public static final String NOMBRE_ASESOR = "nomAsesor";
    public static final String MAESTRIA = "maestria";
    public static final String DOCTORADO = "doctorado";
    public static final String FECHA = "fecha";

    public static final String CREAR_TABLA_EXPOSITOR = "CREATE TABLE " + TABLA_EXPOSITOR + " ( " + ID_EXPOSITOR + " INTEGER, " + NOMBRE_EXPOSITOR
            + " TEXT, " + NOMBRE_EVALUADOR + " TEXT, " + NOMBRE_ASESOR + " TEXT, " + MAESTRIA + " TEXT, " + DOCTORADO + " TEXT, " + FECHA + " TEXT )";

    public static final String TABLA_PRESENTACION = "presentacion";
    public static final String DESC_PRE = "descPresentacion";
    public static final String PUN_PRE = "punPresentacion";

    public static final String CREAR_TABLA_PRESENTACION = "CREATE TABLE " + TABLA_PRESENTACION + " ( " + DESC_PRE + " TEXT, "
            + PUN_PRE + " INTEGER )";


    public static final String TABLA_ESTILO = "estilo";
    public static final String DESC_ESTILO = "descEstilo";
    public static final String PUN_ESTILO = "punEstilo";


    public static final String CREAR_TABLA_ESTILO = "CREATE TABLE " + TABLA_ESTILO + " ( " + DESC_ESTILO + " TEXT, "
            + PUN_ESTILO + " INTEGER )";
}
