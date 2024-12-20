package org.wit.placemark.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlacemarkModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var rating: Int = 0,
                          var image: Uri = Uri.EMPTY,
                          var lat : Double = 0.0,
                          var lng: Double = 0.0,
                          var zoom: Float = 0f,
                          var userId: Long = 0) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
