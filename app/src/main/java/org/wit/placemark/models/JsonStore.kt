//package org.wit.placemark.models
//import android.content.Context
//import com.google.gson.GsonBuilder
//import com.google.gson.reflect.TypeToken
//import org.wit.placemark.helpers.*
//import timber.log.Timber
//import java.lang.reflect.Type
//import java.util.*
//
//const val UNIFIED_JSON_FILE = "data.json"
//val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
//val listType: Type = object : TypeToken<MutableList<UserModel>>() {}.type
//
//class JsonStore(private val context: Context) {
//
//    var users = mutableListOf<UserModel>()
//
//    init {
//        if (exists(context, UNIFIED_JSON_FILE)) {
//            deserialize()
//        }
//    }
//
//    fun findAllUsers(): List<UserModel> {
//        logAll()
//        return users
//    }
//
//    fun findUserById(userId: Long): UserModel? {
//        return users.find { it.id == userId }
//    }
//
//    fun createUser(user: UserModel) {
//        user.id = generateRandomId()
//        users.add(user)
//        serialize()
//    }
//
//    fun addPlacemarkToUser(userId: Long, placemark: PlacemarkModel) {
//        val user = findUserById(userId)
//        user?.let {
//            placemark.id = generateRandomId()
//            it.placemarks.add(placemark)
//            serialize()
//        }
//    }
//
//    private fun serialize() {
//        val jsonString = gsonBuilder.toJson(users, listType)
//        write(context, UNIFIED_JSON_FILE, jsonString)
//    }
//
//    private fun deserialize() {
//        val jsonString = read(context, UNIFIED_JSON_FILE)
//        users = gsonBuilder.fromJson(jsonString, listType)
//    }
//
//    private fun logAll() {
//        users.forEach { Timber.i("$it") }
//    }
//}
