package cl.usm.tallerappsmoviles1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JulioAndres on 3/31/16.
 */
public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "siga_app.db";
    private static final int DB_VERSION = 2;
    private static final String CREATE_TABLE_RAMOS ="CREATE TABLE " +
            "ramos (codigo TEXT, nombre TEXT,descripcion TEXT, profesor TEXT)";

    public static final String TABLE_RAMOS= "ramos";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_PROFESOR = "profesor";

    public AppDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_TABLE_RAMOS);
        crearDatosFalsos(database);
    }

    private void crearDatosFalsos(SQLiteDatabase database) {

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CODIGO, "INF-001");
        cv.put(COLUMN_NOMBRE, "Inteligencia Artificial");
        cv.put(COLUMN_DESCRIPCION, "computador vs humanos");
        cv.put(COLUMN_PROFESOR, "Profesora Elizabeth");

        database.insert(TABLE_RAMOS, null, cv);

        cv.clear();

        cv.put(COLUMN_CODIGO, "INF-002");
        cv.put(COLUMN_NOMBRE, "Computacion Científica 2");
        cv.put(COLUMN_DESCRIPCION, "algoritmos y otros");
        cv.put(COLUMN_PROFESOR, "Profesora No me acuerdo como se llama");

        database.insert(TABLE_RAMOS, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldDBVersion, int newDBVersion) {

    }
}
