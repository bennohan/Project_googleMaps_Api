package com.example.googlemaps

import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.extension.checkLocationPermission
import com.example.googlemaps.databinding.ActivityMainBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mapview.onCreate(savedInstanceState)

        checkLocationPermission {
            listenLocationChange()
        }

        Places.initialize(applicationContext,"AIzaSyA93v2ZHUjqw81UV22_u-Y0Uam3nc4fc5Q")

        val autocompleteImplFragment = supportFragmentManager.findFragmentById(R.id.fragment_autoComplete) as AutocompleteSupportFragment
        autocompleteImplFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))
        autocompleteImplFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener{
            override fun onPlaceSelected(place: Place) {
                Log.i("Latihan_autoComplete","Place:${place.name},${place.id}")
            }

            override fun onError(status: Status) {
                Log.i("Latihan_autoComplete","An Error Accuered : $status")
            }
        })

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