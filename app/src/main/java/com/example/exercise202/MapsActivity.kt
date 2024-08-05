package com.example.exercise202

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val fusedLocationProviderClient by lazy {
        LocationServices
            .getFusedLocationProviderClient(this)
    }

    private lateinit var mMap: GoogleMap

    private var marker: Marker? = null

    private var carMarker: Marker? = null

    private lateinit var requestPermissionLauncher:
            ActivityResultLauncher<String>

    private val parkedButton: View by lazy {
        findViewById(R.id.parked_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        parkedButton.setOnClickListener {
            if (hasLocationPermission()) {
                markParkedCar()
            }
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                    isGranted ->
                if (isGranted) { getLastLocation(::updateMapLocationWithMarker) }
                else {
                    showPermissionRationale {
                        requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                    }
                }
            }


    }

    private fun getLastLocation(onLocation: (location: Location) -> Unit) {
        if (checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PERMISSION_GRANTED && checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    onLocation(it)
                }
            }
    }

    override fun onResume() {
        super.onResume()

        if (hasLocationPermission()) {
            getLastLocation(::updateMapLocationWithMarker)
        }
    }


    private fun updateMapLocation(location: LatLng) {
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(location, 7f))
    }

    private fun addMarkerAtLocation(location: LatLng, title: String,
                                    markerIcon: BitmapDescriptor? = null) =
        mMap.addMarker(MarkerOptions().title(title).position(location).apply {
            markerIcon?.let { icon(markerIcon) }
        })

    private fun getBitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? {
        val bitmap = ContextCompat.getDrawable(this,
            vectorDrawableResourceId)?.let { vectorDrawable ->
            vectorDrawable.setBounds(0, 0,
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight)

            val drawableWithTint = DrawableCompat.wrap(vectorDrawable)
            DrawableCompat.setTint(drawableWithTint, Color.BLUE)

            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bitmap)
            drawableWithTint.draw(canvas)
            bitmap
        }
        return bitmap?.let {
            BitmapDescriptorFactory.fromBitmap(it)
                .also { bitmap.recycle() }
        }
    }

    private fun hasLocationPermission() =
        checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PERMISSION_GRANTED && checkSelfPermission(
            this,
            ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED


    private fun showPermissionRationale(positiveAction: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Location permission")
            .setMessage("This app will not work without knowing your current location")
            .setPositiveButton(android.R.string.ok) { _, _ -> positiveAction() }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create().show()
    }

    private fun markParkedCar() {
        carMarker?.remove()
        carMarker = marker?.position?.let {
            addMarkerAtLocation(
                it,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.car)
            )
        }

        carMarker?.position?.let { saveLocation(it) }

//        getLastLocation { location ->
//            val userLocation = updateMapLocationWithMarker(location)
//            carMarker?.remove()
//            carMarker = addMarkerAtLocation(
//                userLocation,
//                "Your Car",
//                getBitmapDescriptorFromVector(R.drawable.car)
//            )
//            saveLocation(userLocation)
//        }
    }

    private fun updateMapLocationWithMarker(location: Location): LatLng {
        val userLocation = LatLng(location.latitude, location.longitude)
        updateMapLocation(userLocation)
        marker?.remove()
        marker = addMarkerAtLocation(userLocation, "You")
        return userLocation
    }
    private fun addOrMoveSelectedPositionMarker(latLng: LatLng) {
        if (marker == null) {
            marker = addMarkerAtLocation(latLng,
                "You")
        } else { marker?.apply { position = latLng } }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap = googleMap.apply {
            setOnMapClickListener { latLng ->
                addOrMoveSelectedPositionMarker(latLng)
            }
        }

        restoreLocation()?.let { userLocation ->
            carMarker = addMarkerAtLocation(
                userLocation,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.car)
            )
            marker = addMarkerAtLocation(userLocation, "You")
        }

        when {
            hasLocationPermission() -> getLastLocation(::updateMapLocationWithMarker)
            shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
            else -> {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun saveLocation(latLng: LatLng) =
        getPreferences(MODE_PRIVATE)?.edit()?.apply {
            putString("latitude", latLng.latitude.toString())
            putString("longitude", latLng.longitude.toString())
            apply()
        }

    private fun restoreLocation() =
        getPreferences(Context.MODE_PRIVATE)?.let { sharedPreferences ->
            val latitude =
                sharedPreferences.getString("latitude", null)?.toDoubleOrNull() ?: return null
            val longitude =
                sharedPreferences.getString("longitude", null)?.toDoubleOrNull() ?: return null
            LatLng(latitude, longitude)
        }


}