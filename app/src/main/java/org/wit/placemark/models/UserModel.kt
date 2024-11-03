package org.wit.placemark.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(var id: Long = 0,
                     var email: String = "",
                     var password: String = "",
                     var placemarks: MutableList<PlacemarkModel> = mutableListOf()) : Parcelable
