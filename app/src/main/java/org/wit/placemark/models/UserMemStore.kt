package org.wit.placemark.models

import timber.log.Timber.i

var lastUserId = 0L

internal fun getUserId(): Long {
    return lastUserId++
}

class UserMemStore : UserStore {

    private val users = ArrayList<UserModel>()

    override fun findAll(): List<UserModel> {
        return users
    }

    override fun create(user: UserModel) {
        user.id = generateNextId()
        users.add(user)
        logAll()
    }

    override fun update(user: UserModel) {
        val foundUser: UserModel? = users.find { it.id == user.id }
        if (foundUser != null) {
            foundUser.email = user.email
            foundUser.password = user.password
            logAll()
        }
    }

    override fun findByEmail(email: String): UserModel? {
        return users.find { it.email == email }
    }

    override fun generateNextId(): Long {
        return lastUserId++
    }

    private fun logAll() {
        users.forEach { i("${it}") }
    }
}
