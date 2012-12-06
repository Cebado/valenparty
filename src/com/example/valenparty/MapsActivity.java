package com.example.valenparty;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MapsActivity extends MapActivity{
	
	private MapView mapa = null;
	
	Button btnCentrar = null;
	MapController controlMapa = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps_activity);
		
        //Obtenemos una referencia al control MapView
        mapa = (MapView)findViewById(R.id.mapa);
 
        //Mostramos los controles de zoom sobre el mapa
        mapa.setBuiltInZoomControls(true);
        //mapa.setSatellite(true);
        
        
		//Button btnCentrar = null;
		//MapController controlMapa = null;
	
		btnCentrar = (Button) findViewById(R.id.buscameB);
	
		controlMapa = mapa.getController();
	
		btnCentrar.setOnClickListener(new OnClickListener() {
			
		    public void onClick(View arg0) {
		        //Double latitud = 39.18*1E6;
		        //Double longitud = -0.34*1E6;
		        
		        GPSTracker gps;
	            gps = new GPSTracker(MapsActivity.this);
	               
	                // comprobamos si el GPS esta activado
	                if(gps.canGetLocation()){
	 
	                	double latitud = gps.getLatitude();
	                	double longitud = gps.getLongitude();
	                	
                	
	    		        GeoPoint mipunto =
	    			            new GeoPoint((int) (latitud*1000000), (int) (longitud*1000000));
    			 
    			        controlMapa.setCenter(mipunto);
    			        controlMapa.setZoom(19);

    			        Toast.makeText(getApplicationContext(), "Tu posición es - \nLatitud: " + (int) (latitud*1000000) + "\nLongitud: " + (int) (longitud*1000000), Toast.LENGTH_LONG).show();

	                }else{

	                    gps.showSettingsAlert();
	                }
		        
		
		    }
		});




	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


	//FUNCIÓN QUE COMPRUEBA SI HAY INTERNET
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		return false;
	}

	

/*	public void centrarmeEnMapa(){

		Button btnCentrar = null;
		MapController controlMapa = null;

		btnCentrar = (Button)findViewById(R.id.buscameB);

		controlMapa = mapa.getController();

		btnCentrar.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View arg0) {
		        Double latitud = 37.40*1E6;
		        Double longitud = -5.99*1E6;

		        GeoPoint loc =
		            new GeoPoint(latitud.intValue(), longitud.intValue());

		        controlMapa.setCenter(loc);
		        controlMapa.setZoom(10);
		    }
		});

	}*/

}
