package org.wit.placemark.main

import android.app.Activity
import android.app.Application
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkMemStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.UserJSONStore
import org.wit.placemark.models.UserMemStore
import org.wit.placemark.models.UserModel
import org.wit.placemark.models.UserStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore
//     val users = UserMemStore()
  lateinit var users: UserStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        // placemarks = PlacemarkMemStore()
//        users = UserMemStore()
        users = UserJSONStore(applicationContext)
        placemarks = PlacemarkJSONStore(applicationContext)
        i("Placemark started")

    }

}