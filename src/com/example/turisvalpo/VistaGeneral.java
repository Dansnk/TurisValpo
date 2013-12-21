package com.example.turisvalpo;

import com.example.turisvalpo.interfaces.LocationListenerHandler;
import com.example.turisvalpo.location.ActiveLocationManagerActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.SupportMapFragment;


import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class VistaGeneral extends ActiveLocationManagerActivity {
	
	private GoogleMap mMap;
	private PolygonOptions cAlegre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_general);
		
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
		LatLng valparaiso = new LatLng(-33.045832,-71.620309);
		CameraPosition camPos = new CameraPosition.Builder()
		        .target(valparaiso)   //Centramos el mapa en Madrid
		        .zoom(13)         //Establecemos el zoom en 19
		        .bearing(185)      //Establecemos la orientación con el noreste arriba
		        .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
		        .build();
		 
		CameraUpdate camUpd3 =
		    CameraUpdateFactory.newCameraPosition(camPos);
		 
		mMap.animateCamera(camUpd3);
		
		mostrarLineas();
		
		listener = new LocationListenerHandler() {
			@Override
			public void OnLocationReceived(Location loc) {
			}
        };
        
        
        
        mMap.setOnMapClickListener(new OnMapClickListener() {
            public void onMapClick(LatLng point) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(point);
         
                Toast.makeText(
                    VistaGeneral.this,
                    "Click\n" +
                    "Lat: " + point.latitude + "\n" +
                    "Lng: " + point.longitude + "\n" +
                    "X: " + coord.x + " - Y: " + coord.y,
                    Toast.LENGTH_SHORT).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_general, menu);
		return true;
	}
	private void mostrarLineas()
	{
	    //Dibujo con Lineas
	 
		cAlegre = new PolygonOptions()
        .add(new LatLng(-33.039293,-71.629829),
             new LatLng(-33.040462,-71.627535),
             new LatLng(-33.041524,-71.628608),
             new LatLng(-33.043313,-71.62496),
             new LatLng(-33.045984,-71.631837),
             new LatLng(-33.044249,-71.631279),
             new LatLng(-33.044078,-71.633511),
             new LatLng(-33.042351,-71.631569),
             new LatLng(-33.039914,-71.631258));

		cAlegre.strokeWidth(8);
		cAlegre.strokeColor(Color.RED);
		
		mMap.addPolygon(cAlegre);	
		}

}
