package com.example.josequaltask.main.viewModel

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.josequaltask.main.model.MapItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class HomeViewModel(application: Application)  : AndroidViewModel(application) {
    val mapItems= listOf(
        MapItem("Place 1","Cafe Place", LatLng(37.7749, 37.4194)),
        MapItem("Place 2","Cake Shop Place" ,LatLng(37.7749, 38.4294)),
        MapItem("Place 3","Juice Place" ,LatLng(37.7549, 38.9294)),
        MapItem("Place 4","Kunafa Place" ,LatLng(37.49, 38.49)),
        MapItem("Place 5","Rice Place" ,LatLng(36.7749, 38.494)),

    )

    fun addMarkers(googleMap: GoogleMap) {

        mapItems.forEach {
            val markerLocation = it.position
            val markerOptions = MarkerOptions()
                .position(markerLocation)
                .title(it.title)
                .snippet(it.description)
            googleMap.addMarker(markerOptions)
        }
    }
    fun showItemDetailDialog(marker: Marker, context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle(marker.title)
        dialogBuilder.setMessage(marker.snippet)
        dialogBuilder.setPositiveButton("OK") { _, _ ->
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }
     fun moveCameraToPosition(position: LatLng,googleMap: GoogleMap) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12f))
    }
}