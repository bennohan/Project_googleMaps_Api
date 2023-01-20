package com.example.googlemaps

import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.extension.checkLocationPermission
import com.example.googlemaps.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mapview.onCreate(savedInstanceState)

        checkLocationPermission {
            listenLocationChange()
        }

    }


    override fun retrieveLocationChange(location: Location) {
        Log.d("lokasi device", "lat: ${location.latitude} lng: ${location.longitude}")

        binding.mapview.getMapAsync { googleMap ->

            val latLng = LatLng(location.latitude, location.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f))

//            googleMap.addMarker(markerOptions { (R.drawable.marker_map) })
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapview.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        binding.mapview.onSaveInstanceState(outState)

    }
}