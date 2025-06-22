package com.s23010174.joblink;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapPageActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_page);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        checkLocationPermission();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            new AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("Allow location access", (dialog, which) -> {
                        ActivityCompat.requestPermissions(MapPageActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                LOCATION_PERMISSION_REQUEST_CODE);
                    })
                    .setNegativeButton("Ask me later", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        } else {
            enableMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));
                }
                addJobMarkers();
            });
        }
    }

    private void addJobMarkers() {
        List<String> addresses = new ArrayList<>();
        addresses.add("311 Kumarathunga Mawatha, Matara 81000"); // Delivery Driver
        addresses.add("86 New Tangalle Rd, Matara 81000"); // Sales Assistant
        addresses.add("Sunanda Mawatha, Matara 81000"); // Event Helper
        addresses.add("342 Akuressa Road, Godagama, Matara"); // Warehouse Assistant
        addresses.add("07 Beach Rd Matara, Matara 81000"); // Barista

        List<String> titles = new ArrayList<>();
        titles.add("Delivery Driver");
        titles.add("Sales Assistant");
        titles.add("Event Helper");
        titles.add("Warehouse Assistant");
        titles.add("Barista");

        int[] markerDrawableIds = {
                R.drawable.map_marker_blue_bg,
                R.drawable.map_marker_green_bg,
                R.drawable.map_marker_yellow_bg,
                R.drawable.map_marker_blue_bg,
                R.drawable.map_marker_green_bg
        };

        new Thread(() -> {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            for (int i = 0; i < addresses.size(); i++) {
                try {
                    List<Address> addressList = geocoder.getFromLocationName(addresses.get(i), 1);
                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng jobLocation = new LatLng(address.getLatitude(), address.getLongitude());
                        String jobTitle = titles.get(i);
                        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(markerDrawableIds[i]);

                        runOnUiThread(() -> {
                            if (markerIcon != null) {
                                mMap.addMarker(new MarkerOptions()
                                        .position(jobLocation)
                                        .title(jobTitle)
                                        .icon(markerIcon));
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private BitmapDescriptor getMarkerIconFromDrawable(@DrawableRes int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        if (drawable == null) return null;

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        width = width > 0 ? width : 50; // provide a default size
        height = height > 0 ? height : 50; // provide a default size

        drawable.setBounds(0, 0, width, height);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
} 