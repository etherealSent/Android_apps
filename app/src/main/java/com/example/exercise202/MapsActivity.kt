package com.example.exercise202

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
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

    private lateinit var requestPermissionLauncher:
            ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                    isGranted ->
                if (isGranted) { getLastLocation() }
                else {
                    showPermissionRationale {
                        requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
//                        requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)

                    }
                }
            }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap = googleMap.apply {
            setOnMapClickListener { latLng ->
                addOrMoveSelectedPositionMarker(latLng)
            }
        }

        when {
            hasLocationPermission() -> getLastLocation()
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

    private fun showPermissionRationale(positiveAction: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Location permission")
            .setMessage("This app will not work without knowing your current location")
            .setPositiveButton(android.R.string.ok) { _, _ -> positiveAction() }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create().show()
    }

    private fun hasLocationPermission() =
        checkSelfPermission(this, ACCESS_FINE_LOCATION) ==
                PERMISSION_GRANTED

    private fun getLastLocation() {
        if (checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PERMISSION_GRANTED && checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val userLocation = LatLng(
                        location.latitude, location.longitude)
                    updateMapLocation(userLocation)
                    addMarkerAtLocation(userLocation, "You")
                } }    }

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
            DrawableCompat.setTint(drawableWithTint, Color.RED)

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

    private fun addOrMoveSelectedPositionMarker(latLng: LatLng) {
        if (marker == null) {
            marker = addMarkerAtLocation(latLng,
                "Deploy here", getBitmapDescriptorFromVector(
                    R.drawable.target_icon)
            )
        } else { marker?.apply { position = latLng } }
    }

}