package com.example.valenparty;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnShowLocation;
    
    // GPSTracker class
    GPSTracker gps;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Caca de la vaca 7*/
        /*Comentario de prueba POR MI again*/
        /*COMENTARIO MIGUEL ANGEL*/
        /*comentario de Guanter*/
    
    
        
        /* PRUEBA DE USO DE LA CLASE GPS */
        btnShowLocation = (Button) findViewById(R.id.button1);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // creamos un objeto de la clase
                gps = new GPSTracker(MainActivity.this);
 
                // comprobamos si el GPS esta activado
                if(gps.canGetLocation()){
 
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
 
                    
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                	//PRUEBA DE GIT
                    gps.showSettingsAlert();
                }
 
            }
        });
        /* FIN DE LA PRUEBA DE USO DE LA CLASE GPS */
    
    
    
    
    
    
    
    
    
    
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }





}




