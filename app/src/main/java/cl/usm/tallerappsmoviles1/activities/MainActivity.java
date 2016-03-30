package cl.usm.tallerappsmoviles1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cl.usm.tallerappsmoviles1.R;

public class MainActivity extends AppCompatActivity {

    private EditText usuarioEdiText;
    public static String KEY_USUARIO ="USUARIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        usuarioEdiText = (EditText) findViewById(R.id.usuarioEditText);

        Button buttonEntrar = (Button) findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = usuarioEdiText.getText().toString();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(KEY_USUARIO, usuario);
                startActivity(intent);
            }
        });
    }
}
