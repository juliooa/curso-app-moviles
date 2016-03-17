package cl.usm.tallerappsmoviles1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String nombreUsuario = getIntent().getStringExtra(MainActivity.KEY_USUARIO);

        TextView textViewNombreUsuario =
                (TextView) findViewById(R.id.textViewNombreUsuario);

        textViewNombreUsuario.setText(nombreUsuario);
    }
}
