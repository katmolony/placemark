package org.wit.placemark.views.placemarklist

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.wit.placemark.views.map.PlacemarkMapView
import org.wit.placemark.views.placemark.PlacemarkView
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.views.login.LoginView
import android.app.AlertDialog
import android.widget.EditText


class PlacemarkListPresenter(val view: PlacemarkListView) {

    var app: MainApp
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    private var position: Int = 0

    var isSearching: Boolean = false

    init {
        app = view.application as MainApp
        registerMapCallback()
        registerRefreshCallback()
    }

    fun getPlacemarks() = app.placemarks.findAll()

    fun doAddPlacemark() {
        val launcherIntent = Intent(view, PlacemarkView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doEditPlacemark(placemark: PlacemarkModel, pos: Int) {
        val launcherIntent = Intent(view, PlacemarkView::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        position = pos
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doShowPlacemarksMap() {
        val launcherIntent = Intent(view, PlacemarkMapView::class.java)
        mapIntentLauncher.launch(launcherIntent)
    }

    fun doShowPlacemarkLogin() {
        val launcherIntent = Intent(view, LoginView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doSearchPlacemark() {
        val searchEditText = EditText(view)
        AlertDialog.Builder(view)
            .setTitle("Search Placemark")
            .setView(searchEditText)
            .setPositiveButton("Search") { dialog, which ->
                val searchTerm = searchEditText.text.toString()
                filterPlacemarks(searchTerm)
                isSearching = true
                view.updateMenu()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    fun clearSearch() {
        view.updatePlacemarks(getPlacemarks())
        isSearching = false
        view.updateMenu()
    }

    private fun filterPlacemarks(searchTerm: String) {
        val filteredPlacemarks = app.placemarks.findAll().filter {
            it.title.contains(searchTerm, ignoreCase = true) // Ignore case for search
        }
        view.updatePlacemarks(filteredPlacemarks) // Update the view with the filtered list
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            view.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) view.onRefresh()
                else // Deleting
                    if (it.resultCode == 99) view.onDelete(position)
            }
    }
    private fun registerMapCallback() {
        mapIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {  }
    }
}