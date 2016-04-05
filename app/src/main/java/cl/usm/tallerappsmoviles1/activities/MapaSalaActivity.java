package cl.usm.tallerappsmoviles1.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import cl.usm.tallerappsmoviles1.R;


public class MapaSalaActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        LocationListener {

    private GoogleApiClient googleApiClient;
    private TextView textViewUbicacion;
    private static final int MY_PERMISSIONS_REQUEST = 1;
    private LocationRequest locationRequest;
    private Location ultimaUbicacion;

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopLocationUpdates();
        googleApiClient.disconnect();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_sala);

        textViewUbicacion = (TextView)findViewById(R.id.textViewUbicacion);

        iniciarGooglePlayServicesApiClient();
        crearLocationRequest();
    }

    private void crearLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private void iniciarGooglePlayServicesApiClient() {

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        obtenerUltimaUbicacion();
        startLocationUpdates();

    }

    protected void startLocationUpdates() throws SecurityException{

        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    obtenerUltimaUbicacion();

                } else {
                    //Permiso denegado
                }
                return;
            }
        }
    }

    private void obtenerUltimaUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED  ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST);
            return;
        }

        ultimaUbicacion = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(ultimaUbicacion!=null) {
            String ubicacion = ultimaUbicacion.getLatitude() + ", " +
                    ultimaUbicacion.getLongitude();

            textViewUbicacion.setText(ubicacion);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        ultimaUbicacion = location;
        updateUI();
    }

    private void updateUI() {

        if (ultimaUbicacion == null) {
            textViewUbicacion.setText("no existe ultima ubicación");
        } else {
            String oldStatus = textViewUbicacion.getText().toString();
            String newStatus = "" +ultimaUbicacion.getLatitude() +
                    ", " + ultimaUbicacion.getLongitude();

            textViewUbicacion.setText(oldStatus + "\n" + newStatus);
        }
    }
}
