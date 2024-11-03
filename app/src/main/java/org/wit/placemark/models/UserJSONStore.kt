package org.wit.placemark.models

import android.content.Context
import org.wit.placemark.helpers.*
import timber.log.Timber

const val JSON_USER_FILE = "users.json"

class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, JSON_USER_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }

    override fun create(user: UserModel) {
        user.id = generateRandomId()
        users.add(user)
        serialize()
    }

    override fun update(user: UserModel) {
        val foundUser = users.find { it.id == user.id }
        if (foundUser != null) {
            foundUser.apply {
                email = user.email
                password = user.password
            }
            serialize()
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, listType<UserModel>())
        write(context, JSON_USER_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_USER_FILE)
        users = gsonBuilder.fromJson(jsonString, listType<UserModel>())
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}
