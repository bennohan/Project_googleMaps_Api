package com.example.googlemaps

import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.extension.checkLocationPermission
import com.example.googlemaps.databinding.ActivityMainBinding


class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLocationPermission {
            listenLocationChange()
        }

        binding.mapview.getMapAsync { googleMap ->
        }
    }

//    override fun retrieveLocationChange(location: Location) {
//        Log.d("lokasi device","latitude: ${location.latitude} longitude: ${location.longitude}")
//    }

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()        binding.mapview.onDestroy()
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