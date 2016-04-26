package cl.usm.tallerappsmoviles1.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cl.usm.tallerappsmoviles1.R;
import cl.usm.tallerappsmoviles1.model.Ramo;
import cl.usm.tallerappsmoviles1.model.RamosDTO;

public class InscripcionActivity extends AppCompatActivity {

    private static final String getCursosURL = "http://wearenicecorp.com/apps/siga_app/cursos.php";

    //String[] ramos = { "Quimica",
      //      "Mate I",
        //    "Fisica",
          //  "Progra <3"};
    ArrayList<Ramo> ramos = new ArrayList<>();
    private CursosAdapter cursosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        ListView listViewRamos = (ListView) findViewById(R.id.listViewRamos);

        cursosAdapter = new CursosAdapter();
        listViewRamos.setAdapter(cursosAdapter);

        GetRamosAsync getRamosAsync = new GetRamosAsync();
        getRamosAsync.execute();
    }

    private class CursosAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return ramos.size();
        }

        @Override
        public Ramo getItem(int pos) {
            return ramos.get(pos);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Ramo ramo = getItem(position);

            View row = getLayoutInflater().inflate(R.layout.fila_curso, parent, false);

            TextView nombreTextView = (TextView)row.findViewById(R.id.textViewFilaNombreCurso);
            nombreTextView.setText(ramo.getNombre());

            TextView descripcionTextView = (TextView)row.findViewById(R.id.textViewFilaDescCurso);
            descripcionTextView.setText(ramo.getDescripcion());

            return row;
        }
    }

    private class GetRamosAsync extends AsyncTask<String, Integer, ArrayList<Ramo>>{

        @Override
        protected ArrayList<Ramo> doInBackground(String... strings) {
            //ejecución en background
            try {
                HttpURLConnection httpURLConnection =
                        (HttpURLConnection)new URL(getCursosURL).openConnection();


                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                RamosDTO ramosObtenidos = new Gson().fromJson(reader, RamosDTO.class);
                reader.close();

                return ramosObtenidos.getRamos();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Ramo> ramosObtenidos) {
            super.onPostExecute(ramosObtenidos);

            ramos.addAll(ramosObtenidos);
            cursosAdapter.notifyDataSetChanged();
        }

    }
}
