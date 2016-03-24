package cl.usm.tallerappsmoviles1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InscripcionActivity extends AppCompatActivity {

    String[] ramos = { "Quimica",
            "Mate I",
            "Fisica",
            "Progra <3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        ListView listViewRamos = (ListView) findViewById(R.id.listViewRamos);

        RamosAdapter ramosAdapter =  new RamosAdapter(this,
                R.layout.fila_curso,
                ramos);
        listViewRamos.setAdapter(ramosAdapter);
    }

    private class RamosAdapter extends ArrayAdapter<String>{

        public RamosAdapter(Context context, int resource, String[] objects) {
            super(context, resource,R.id.textViewFilaNombreCurso, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = super.getView(position, convertView, parent);

            TextView descCursoTextView = (TextView)row.findViewById(R.id.textViewFilaDescCurso);
            descCursoTextView.setText("descripcion curso");

            return row;
        }
    }
}
