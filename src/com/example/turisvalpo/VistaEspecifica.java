package com.example.turisvalpo;

import com.example.turisvalpo.interfaces.LocationListenerHandler;
import com.example.turisvalpo.location.ActiveLocationManagerActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

public class VistaEspecifica extends ActiveLocationManagerActivity {
	
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_especifica);
		
		mMap = ((SupportMapFragment) getSupportFragmentManager()
										.findFragmentById(R.id.map)).getMap();
		
		listener = new LocationListenerHandler() {
			@Override
			public void OnLocationReceived(Location loc) {
				setUpMap(loc);
			}
        };
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setUpMap(Location currentLocation){
		if(mMap != null){
			if(currentLocation != null){
				CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(),
																				currentLocation.getLongitude()));
				CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
				
				mMap.moveCamera(center);
			    mMap.animateCamera(zoom);
			    mMap.getUiSettings().setZoomControlsEnabled(false);
			    mMap.setMyLocationEnabled(true);
			    
			    mMap.setOnMapClickListener(new OnMapClickListener() {
					
					@Override
					public void onMapClick(LatLng arg0) {
						
					}
				});
			    
			    mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
					
					@Override
					public boolean onMarkerClick(Marker arg0) {
						
						return false;
					}
				});
			}
		}
	}

}