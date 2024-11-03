package org.wit.placemark.models

interface UserStore {
    fun findAll(): List<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun findByEmail(email: String): UserModel?
    fun generateNextId(): Long
}