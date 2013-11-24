package com.example.turisvalpo;

import com.example.turisvalpo.interfaces.LocationListenerHandler;
import com.example.turisvalpo.location.ActiveLocationManagerActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

public class VistaGeneral extends ActiveLocationManagerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_general);
		
		listener = new LocationListenerHandler() {
			@Override
			public void OnLocationReceived(Location loc) {
			}
        };

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_general, menu);
		return true;
	}

}
