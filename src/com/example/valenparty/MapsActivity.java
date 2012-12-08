package com.example.valenparty;


import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MapsActivity extends MapActivity{
	
	protected static GeoPoint mipunto = null;
	private MapView mapa = null;
	private boolean vistaSatelite = false;
	private boolean desplazaAnimado = false;
	
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
        
        //Mostramos la vista Satelite dependiendo de la configuracion puesta
        if (!vistaSatelite){
        	mapa.setSatellite(false);
        }else{
        	mapa.setSatellite(false);
        }
       

        
        

        
        
        
        
        
        
        
	
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
	                	
	                	

                	
	    		        mipunto =
	    			            new GeoPoint((int) (latitud*1000000), (int) (longitud*1000000));
	    		        
	    		       if (!desplazaAnimado){
	    		    	   controlMapa.setCenter(mipunto);
	    		    	   controlMapa.setZoom(19);
	    		       }else{
	    		    	    controlMapa.animateTo(mipunto);
    			            int zoomActual = mapa.getZoomLevel();
    			     
    			            for(int i=zoomActual; i<19; i++)
    			                controlMapa.zoomIn();
	    		       }
    			        
  			        

    			        Toast.makeText(getApplicationContext(), "Tu posición es - \nLatitud: " + (int) (latitud*1000000) + "\nLongitud: " + (int) (longitud*1000000), Toast.LENGTH_LONG).show();

	                }else{

	                    gps.showSettingsAlert();
	                }
	                
	                /*
	                 * MOSTRAR CAPAS
	                 * 
	                 * 
	                 */
	                
	              //Añadimos la capa de marcadores
	                List<Overlay> capas = mapa.getOverlays();
	                MyInfoExtraMaps minfo = new MyInfoExtraMaps(mipunto);
	                capas.add(minfo);
	                mapa.postInvalidate();
	                
	                
	                /**
	                 *  FIN MOSTRAR CAPAS
	                 * 
	                 * */
		
		    }
		});

	}

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps_activity, menu);
        return true;
    }
	

	
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()) {
		case R.id.vista_satelite:

			if (item.isChecked()){
				item.setChecked(false);
				//cambiamos la variable global que regula esto
				vistaSatelite = false;
				
			}else{
				item.setChecked(true);
				//cambiamos la variable global que regula esto
				vistaSatelite = true;
			}
			
			
			break;
		case R.id.anim_activ:

			if (item.isChecked()){
				item.setChecked(false);
				//cambiamos la variable global que regula esto
				desplazaAnimado = false;
			}else{
				item.setChecked(true);
				//cambiamos la variable global que regula esto
				desplazaAnimado = true;
			}
			
			break;	

		default:

			break;
		}
    	
        if (!vistaSatelite){
        	mapa.setSatellite(false);
        }else{
        	mapa.setSatellite(true);
        }
    	
		return false;
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
