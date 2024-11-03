package org.wit.placemark.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.placemark.main.MainApp
import android.content.Intent
import android.net.Uri
import android.app.AlertDialog

class PlacemarkMapPresenter(val view: PlacemarkMapView) {
    private val app: MainApp = view.application as MainApp

    fun doPopulateMap(map: GoogleMap) {
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(view)
        app.placemarks.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doCancel() {
        view.finish()
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as? Long
        val placemark = tag?.let { app.placemarks.findById(it) }
        if (placemark != null) {
            view.showPlacemark(placemark)
            showNavigationDialog(placemark.lat, placemark.lng)
        }
    }

    private fun showNavigationDialog(lat: Double, lng: Double) {
        AlertDialog.Builder(view)
            .setTitle("Navigate")
            .setMessage("Would you like directions to this location?")
            .setPositiveButton("Yes") { _, _ ->
                navigateToPlacemark(lat, lng)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun navigateToPlacemark(lat: Double, lng: Double) {
        val navigationUri = Uri.parse("google.navigation:q=$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW, navigationUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        view.startActivity(mapIntent)
    }
}