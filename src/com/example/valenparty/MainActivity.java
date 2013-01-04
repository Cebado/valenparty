package com.example.valenparty;





import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

//import android.view.Menu;
//import android.view.MenuItem;


import android.view.View;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import android.widget.Button;
import android.widget.ImageButton;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends SherlockActivity {

    Button btnShowLocation;
    ImageButton botongestionamigos;
    
    // clase GPSTracker 
    GPSTracker gps;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  
        botongestionamigos = (ImageButton) findViewById(R.id.imageButton3);
        
        // Evento boton gestor contactos
        botongestionamigos.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
            	startActivity(new Intent(MainActivity.this, gestor_amigos.class));
            }
        });
        
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

    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.activity_main, menu);
       // getMenuInflater().inflate(R.menu.activity_main, menu);

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
    	//startActivity(new Intent(this, MapsActivity.class));
    	startActivity(new Intent(this, MapsActivityV2.class));
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	int itemId = item.getItemId();
		if (itemId == R.id.itemmenumaps) {
			//if (isOnline()){ //si hay conexi�n a internet lo mostramos, sin� no
			launchMostrarMapas(null);
  	//}else{
  	//	Toast.makeText(getApplicationContext(), "Debes estar conectado a Internet para acceder a esta funci�n", Toast.LENGTH_LONG).show();
  	//}
		} else if (itemId == R.id.menu_settings) {
		} else if (itemId == R.id.creditos_settings) {
			startActivity(new Intent(MainActivity.this, CreditosActivity.class));
		} else {
		}
		return false;
    }

    
    
    
    
    /*SOLO EN EL CASO DE QUE "ESTAMOS DE FIESTA" SE LANZA UNA NOTIFICACIÓN QUE NOS LO RECUERDA
     * (non-Javadoc)
     * En el
     * @see com.actionbarsherlock.app.SherlockActivity#onDestroy()
     */
    
    //GESTIONAMOS QUE APAREZCA UNA NOTIFICACIÓN CUANDO ESTAMOS DE FIESTA
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		
		
		
		//Obtenemos una referencia al servicio de notificaciones
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager notManager =
		(NotificationManager) getSystemService(ns);
		
		//Configuramos la notificación
		int icono = R.drawable.partyicont; //R.drawable.amigo;//android.R.drawable.btn_star_big_on;
		CharSequence textoEstado = "ValenParty sigue en segundo plano";
		long hora = System.currentTimeMillis();
		 
		@SuppressWarnings("deprecation")
		Notification notif =
		    new Notification(icono, textoEstado, hora);  
	/*	
		 Notification notif = new Notification.Builder(this)
         .setContentTitle("ValenParty sigue en segundo plano")
         .setContentText("Puedes pulsar para abrir la aplicación de nuevo")
         .setSmallIcon(android.R.drawable.stat_sys_warning)
         .build();
*/
		
		//Configuramos el Intent
		Context contexto = getApplicationContext();
		CharSequence titulo = "ValenParty: estoy de fiesta...!";
		CharSequence descripcion = "...en L'Umbracle. Click para gestionar";
		 
		Intent notIntent = new Intent(contexto,
		    MainActivity.class);
		 
		PendingIntent contIntent = PendingIntent.getActivity(
		    contexto, 0, notIntent, 0);
		 
		//Cuando funcione correctamente se pondran las funciones no @Deprecated
		notif.setLatestEventInfo(
		    contexto, titulo, descripcion, contIntent);
		
		//AutoCancel: cuando se pulsa la notificaión ésta desaparece
		notif.flags |= Notification.FLAG_AUTO_CANCEL;
		 
		//Añadir sonido, vibración y luces
		notif.defaults |= Notification.DEFAULT_SOUND;
		//notif.defaults |= Notification.DEFAULT_VIBRATE;
		//notif.defaults |= Notification.DEFAULT_LIGHTS;
		
		//Enviar notificación
		notManager.notify(1, notif);
		
		super.onDestroy();
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




