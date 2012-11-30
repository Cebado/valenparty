package com.example.valenparty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GPSclass {

	
    private ProgressDialog pd;
    
	LocationManager mLocationManager;
	Location mLocation;
	MyLocationListener mLocationListener;
	
	Location currentLocation = null;
	
	TextView outlat;
	TextView outlong;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        outlat = (TextView) findViewById(R.id.outlat);
        outlong = (TextView) findViewById(R.id.outlong);
        
		Button btsearch = (Button) findViewById(R.id.btsearch);
		btsearch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                writeSignalGPS();
            }
          
        });
        
    }
    
    private void setCurrentLocation(Location loc) {
    	currentLocation = loc;
    }
    
    
    private void writeSignalGPS() {
    	
    	DialogInterface.OnCancelListener dialogCancel = new DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getBaseContext(), 
                        getResources().getString(R.string.gps_signal_not_found), 
                        Toast.LENGTH_LONG).show();
                handler.sendEmptyMessage(0);
            }
          
        };
    	
		pd = ProgressDialog.show(this, this.getResources().getString(R.string.search), 
				this.getResources().getString(R.string.search_signal_gps), true, true, dialogCancel);
		
		Thread thread = new Thread(this);
		thread.start();

    }

	@Override
	public void run() {
    	
		mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			
			Looper.prepare();
			
			mLocationListener = new MyLocationListener();
			
			mLocationManager.requestLocationUpdates(
	                LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
			Looper.loop(); 
			Looper.myLooper().quit(); 
			
		} else {
			
            Toast.makeText(getBaseContext(), 
                    getResources().getString(R.string.gps_signal_not_found), 
                    Toast.LENGTH_LONG).show();
            
		}
	}
    
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
			mLocationManager.removeUpdates(mLocationListener);
	    	if (currentLocation!=null) {
	    		outlat.setText("Latitude: " + currentLocation.getLatitude());
	    		outlong.setText("Longitude: " + currentLocation.getLongitude());
	    	}
		}
	};
	
    private class MyLocationListener implements LocationListener 
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(), 
                    getResources().getString(R.string.gps_signal_found), 
                    Toast.LENGTH_LONG).show();
                setCurrentLocation(loc);
                handler.sendEmptyMessage(0);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    } 
}
