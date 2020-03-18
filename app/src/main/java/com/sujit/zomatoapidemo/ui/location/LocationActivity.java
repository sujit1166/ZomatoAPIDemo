package com.sujit.zomatoapidemo.ui.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.sujit.zomatoapidemo.R;
import com.sujit.zomatoapidemo.databinding.LocationDetectionActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

public class LocationActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    int PERMISSION_ID = 100;
    FusedLocationProviderClient mFusedLocationClient;
    com.sujit.zomatoapidemo.data.models.Location requiredLocation;

    private LocationDetectionActivity binding;
    private LocationCallback mLocationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requiredLocation = new com.sujit.zomatoapidemo.data.models.Location();
        initialiseView();
    }


    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location);
        binding.tvAutoDetect.setOnClickListener(view -> getLastLocation());
        binding.btnOK.setOnClickListener(view -> getLastLocation());
        binding.btnCancel.setOnClickListener(view -> getLastLocation());
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                Log.e(TAG, "getLastLocation: old");
                                requiredLocation.setLatitude(String.valueOf(location.getLatitude()));
                                requiredLocation.setLongitude(String.valueOf(location.getLongitude()));
                                getAddressFromLocation(location.getLatitude(), location.getLongitude());
                            }
                        }
                );
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.location_required))
                        .setMessage(R.string.location_tittle)
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            navigateLocationActivity();
                        })
                        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss())
                        .setCancelable(false);
                builder.create().show();
            }
        } else {
            requestPermissions();
        }
    }

    private void navigateLocationActivity() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 100);
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
//        Log.e(TAG, "requestNewLocationData: ");
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.e(TAG, "onLocationResult: get new location");
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    requiredLocation.setLatitude(String.valueOf(locationResult.getLastLocation().getLatitude()));
                    requiredLocation.setLongitude(String.valueOf(locationResult.getLastLocation().getLongitude()));
                    getAddressFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                }
            }
        };
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());

    }


    private void getAddressFromLocation(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String address = new String();
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                Log.e(TAG, "getAddressFromLocation: " + fetchedAddress);
                if (requiredLocation != null && fetchedAddress != null) {
                    if (!TextUtils.isEmpty(fetchedAddress.getLocality())) {

                        requiredLocation.setAddress(fetchedAddress.getLocality());
                        address = fetchedAddress.getLocality();

                    } else if (!TextUtils.isEmpty(fetchedAddress.getSubAdminArea())) {

                        requiredLocation.setAddress(fetchedAddress.getSubAdminArea());
                        address = fetchedAddress.getSubAdminArea();

                    } else {
                        Toast.makeText(this, R.string.try_again, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, R.string.try_again, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.try_again, Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.try_again, Toast.LENGTH_SHORT).show();
        }
        binding.etCity.setText(address);
    }


    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mFusedLocationClient != null && mLocationCallback != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }
}
