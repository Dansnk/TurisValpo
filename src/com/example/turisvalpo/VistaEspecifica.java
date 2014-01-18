package com.example.turisvalpo;

import com.example.turisvalpo.interfaces.LocationListenerHandler;
import com.example.turisvalpo.location.ActiveLocationManagerActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.drawable.BitmapDrawable;
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
        
        mMap.addMarker(new MarkerOptions()
        		.position(new LatLng(-33.053853,-71.622859))
        		.title("La Sebastiana")
        		.snippet("Texto"));
        
        mMap.addMarker(new MarkerOptions()
        		.position(new LatLng(-33.046497, -71.620971))
        		.title("Museo Historia Natural Valparaiso")
        		.snippet("holi"));
       
        mMap.addMarker(new MarkerOptions()
				.position(new LatLng(-33.05058,-71.618074))
				.title("Ascensor Mariposa")
				.snippet("holi")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.butterfly)));
        
        mMap.addMarker(new MarkerOptions()
				.position(new LatLng(-33.034355,-71.596337))
				.title("Cerro Placeres")
				.snippet("UTFSM")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.usm)));
        
        setMarkerTuristicPlace(new LatLng(-33.038349, -71.628301), "Monumento a los heroes", "Arturo Prat");
        setMarkerMuseum(new LatLng(-33.038569,-71.628685),"Museo In Situ" ,"Cerrado por Daños Estructurales");
        
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
	
	private void setMarkerTuristicPlace(LatLng position, String titulo, String info) {
		  Marker myMaker = mMap.addMarker(new MarkerOptions()
		       .position(position)
		       .title(titulo)
		       .snippet(info)
		       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
		}
	
	private void setMarkerMuseum(LatLng position, String titulo, String info) {
		  Marker myMaker = mMap.addMarker(new MarkerOptions()
		       .position(position)
		       .title(titulo)
		       .snippet(info)
		       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
		}
}