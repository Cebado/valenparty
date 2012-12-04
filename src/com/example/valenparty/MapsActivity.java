package com.example.valenparty;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class MapsActivity extends MapActivity{
	
	private MapView mapa = null;

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
	
	
	
	
	
	
	
	
}
