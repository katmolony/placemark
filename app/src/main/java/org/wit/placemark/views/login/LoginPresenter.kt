package org.wit.placemark.views.login

import android.content.Context
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.placemarklist.PlacemarkListView
import timber.log.Timber.i

class LoginPresenter(val view: LoginView) {

    var app: MainApp = view.application as MainApp

    fun doRegister(email: String, password: String) {
        // Validate inputs
        if (email.isEmpty() || password.isEmpty()) {
            view.showMessage("Please fill all fields")
            return
        }
        if (app.users.findByEmail(email) != null) {
            view.showMessage("Email is already registered")
            return
        }

        val newUser = UserModel(id = app.users.generateNextId(), email = email, password = password)
        app.users.create(newUser)
        app.currentUserId = newUser.id
        view.showMessage("Registration successful!")
        view.navigateToPlacemarkList()
    }

    fun doLogin(email: String, password: String) {
        val user = app.users.findByEmail(email)
        if (user != null && user.password == password) {
            app.currentUserId = user.id
            view.navigateToPlacemarkList()
        } else {
            view.showMessage("Invalid login credentials")
        }
    }

//    fun doLogout() {
//        app.currentUserId = null
//        view.navigateToLoginScreen()
//    }
}
