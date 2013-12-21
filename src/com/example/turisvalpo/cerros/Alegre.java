package com.example.turisvalpo.cerros;

import com.example.turisvalpo.R;
import com.example.turisvalpo.interfaces.LocationListenerHandler;
import com.example.turisvalpo.location.ActiveLocationManagerActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

public class Alegre extends ActiveLocationManagerActivity {
	
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alegre);
		
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
		LatLng valparaiso = new LatLng(-33.043197,-71.627932);
		CameraPosition camPos = new CameraPosition.Builder()
		        .target(valparaiso)   //Centramos el mapa en Madrid
		        .zoom(15)         //Establecemos el zoom
		        .bearing(185)      //Establecemos la orientación con el noreste arriba
		        .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
		        .build();
		 
		CameraUpdate camUpd3 =
		    CameraUpdateFactory.newCameraPosition(camPos);
		 
		mMap.animateCamera(camUpd3);
		
		listener = new LocationListenerHandler() {
			@Override
			public void OnLocationReceived(Location loc) {
			}
        };

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alegre, menu);
		return true;
	}

}
