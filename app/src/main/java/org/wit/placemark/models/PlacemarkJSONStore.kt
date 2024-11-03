package org.wit.placemark.models

import android.content.Context
import org.wit.placemark.helpers.*
import timber.log.Timber

const val JSON_FILE = "placemarks.json"

class PlacemarkJSONStore(private val context: Context) : PlacemarkStore {

    var placemarks = mutableListOf<PlacemarkModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PlacemarkModel> {
        logAll()
        return placemarks
    }

    override fun findById(id: Long): PlacemarkModel? {
        return placemarks.find { it.id == id }
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = generateRandomId()
        placemarks.add(placemark)
        serialize()
    }

    override fun update(placemark: PlacemarkModel) {
        val foundPlacemark = placemarks.find { it.id == placemark.id }
        if (foundPlacemark != null) {
            foundPlacemark.apply {
                title = placemark.title
                description = placemark.description
                rating = placemark.rating
                image = placemark.image
                lat = placemark.lat
                lng = placemark.lng
                zoom = placemark.zoom
            }
            serialize()
        }
    }

    override fun delete(placemark: PlacemarkModel) {
        placemarks.remove(placemark)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(placemarks, listType<PlacemarkModel>())
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        placemarks = gsonBuilder.fromJson(jsonString, listType<PlacemarkModel>())
    }

    private fun logAll() {
        placemarks.forEach { Timber.i("$it") }
    }
}
