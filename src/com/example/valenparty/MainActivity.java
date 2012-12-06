package com.example.valenparty;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnShowLocation;
    
    // clase GPSTracker 
    GPSTracker gps;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        /* PRUEBA DE USO DE LA CLASE GPS 
        btnShowLocation = (Button) findViewById(R.id.button1);
        
        // Evento boton mostrar_ubicacion
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // creamos un objeto de la clase
                gps = new GPSTracker(MainActivity.this);
 
                // comprobamos si el GPS esta activado
                if(gps.canGetLocation()){
 
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
 
                    
                    Toast.makeText(getApplicationContext(), "Tu posici�n es - \nLatitud: " + latitude + "\nLongitud: " + longitude, Toast.LENGTH_LONG).show();
                }else{

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
    
    
    
    //FUNCI�N PARA DETECTAR SI HAY CONEXI�N A INTERNET
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		return false;
	}
	
	
	
    //LANZAMOS LA VENTANA MAPS
    public void launchMostrarMapas(View view) { 
    	
// NO SE POR QU� NO FUNCIONA SI LO PONGO DE MANERA CONDICIONAL A LA CONEXI�N A INTERNET    	
//    	if (isOnline()){
    		startActivity(new Intent(this, MapsActivity.class));
//    	}else{
//    		Toast.makeText(getApplicationContext(), "Debes estar conectado a Internet para acceder a esta funci�n", Toast.LENGTH_LONG).show();
//    	}
    }

    /*
		    <!--  #boton para lanzar la activity MapsActivity
		    <Button
		        android:id="@+id/button2"
		        android:layout_width="wrap_content"
		        android:layout_height="wrap_content"
		        android:layout_alignParentBottom="true"
		        android:layout_centerHorizontal="true"
		        android:layout_marginBottom="49dp"
		        android:onClick="@string/launchMostrarMapas"
		        android:text="@string/showmaps" />
			-->

     */
    
    
    
    
}




