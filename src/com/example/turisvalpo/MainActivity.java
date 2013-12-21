package com.example.turisvalpo;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	long timestamp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button Button1 = (Button) findViewById(R.id.button1);
		final Button Button2 = (Button) findViewById(R.id.button2);
		final Button Button3 = (Button) findViewById(R.id.button3);
		
		@SuppressWarnings("deprecation")
		final Object object = getLastNonConfigurationInstance();
		
		if(object == null)
			Toast.makeText(getApplicationContext(), getString(R.string.bienvenido), Toast.LENGTH_SHORT).show();
		Button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VistaEspecifica.class);
				startActivity(intent);
				
			}
		});
	
		Button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VistaGeneral.class);
				startActivity(intent);
				
			}
		});
	
		
		Button3.setOnClickListener(new OnClickListener() {
			
			@Override 
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Creditos.class);
				startActivity(intent);
				
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onResume(){
		super.onResume();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		
		timestamp = Calendar.getInstance().getTimeInMillis();
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		Object object = new Object();
	    return object;
	}

}
