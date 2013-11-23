package com.example.turisvalpo.location;

import java.util.List;

import com.example.turisvalpo.interfaces.LocationListenerHandler;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActiveLocationManagerActivity extends FragmentActivity implements LocationListener {
	
	protected LocationListenerHandler listener;
	
	private LocationManager locationManager;
	private Location currentBestLocation;
	private final String GPS = LocationManager.GPS_PROVIDER;
	private final String NET = LocationManager.NETWORK_PROVIDER;
	private static final int MIN = 1000 * 60;
	
	private static final long minTime = 10000;
	private static final float minDistance = 200;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		startUpdates();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		stopUpdates();
	}
	
	public void startUpdates(){
		if(!locationManager.isProviderEnabled(NET) && !locationManager.isProviderEnabled(GPS)){
			
		} else {
			if(locationManager.isProviderEnabled(NET)){
				locationManager.requestLocationUpdates(NET, minTime, minDistance, this);
			}
			if(locationManager.isProviderEnabled(GPS)){
				locationManager.requestLocationUpdates(GPS, minTime, minDistance, this);
			}
		}
	}

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix.
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @return true, if is better location
	 */
	private boolean isBetterLocation(Location location) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > MIN;
		boolean isSignificantlyOlder = timeDelta < -MIN;
		boolean isNewer = timeDelta > 0;

		// If it's been more than half minute since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}
	
	public Location getLastKnownLocation(){
		if(locationManager != null){
			List<String> providers = locationManager.getProviders(true);
	        Location l = null;
	        if(providers != null){
		        for (String provider : providers) {
		        	l = locationManager.getLastKnownLocation(provider);
		        	if (l != null) break;
		        }
		        return l;
	        } else {
	        	return null;
	        }
		} else {
			return null;
		}
	}
	
	/**
	 * Checks whether two providers are the same.
	 * 
	 * @param provider1
	 *            the provider1
	 * @param provider2
	 *            the provider2
	 * @return true, if is same provider
	 */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}
	
	public void stopUpdates(){
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		if(isBetterLocation(location)){
			currentBestLocation = location;
			if(listener != null)
				listener.OnLocationReceived(location);
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
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
