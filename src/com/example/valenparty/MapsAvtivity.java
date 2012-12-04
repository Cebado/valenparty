package com.example.valenparty;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
 
import android.os.Bundle;

public class MapsAvtivity extends MapActivity{
	
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
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
