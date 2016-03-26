package cl.usm.tallerappsmoviles1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import javax.net.ssl.CertPathTrustManagerParameters;

public class HomeActivity extends AppCompatActivity {

    String[] opciones = { "Horario personal",
            "Inscripción",
            "Certificados",
            "Ficha personal",
            "Resumen académico", "Encuesta docente"
            , "Planes de Carrera","Deuda","Avance Curricular"};


    private static final int OPCION_INSCRIPCION = 1;
    private static final int OPCION_CERTIFICADOS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String nombreUsuario = getIntent().getStringExtra(MainActivity.KEY_USUARIO);

        TextView textViewNombreUsuario =
                (TextView) findViewById(R.id.textViewBienvenida);

        textViewNombreUsuario.setText("Hola " + nombreUsuario);

        setListViewOpciones();
    }

    private void setListViewOpciones() {

        ListView listViewOpciones = (ListView) findViewById(R.id.listViewOpciones);

        ArrayAdapter<String> adapterOpciones =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        opciones);

        listViewOpciones.setAdapter(adapterOpciones);

        listViewOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                Intent intent;

                switch (position){
                    case OPCION_INSCRIPCION:
                        intent = new Intent(HomeActivity.this,
                                InscripcionActivity.class);
                        startActivity(intent);
                        break;
                    case OPCION_CERTIFICADOS:
                        intent = new Intent(HomeActivity.this,
                                CertificadosActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
