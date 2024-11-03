package org.wit.placemark.main

import android.app.Activity
import android.app.Application
import androidx.core.splashscreen.SplashScreen
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkMemStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.UserModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore
    val users = ArrayList<UserModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        // placemarks = PlacemarkMemStore()
        placemarks = PlacemarkJSONStore(applicationContext)
        i("Placemark started")

    }

}