package org.wit.placemark.views.login

import android.content.Context
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.placemarklist.PlacemarkListView
import timber.log.Timber.i

class LoginPresenter(val view: LoginView, val context: Context) {

    private var app: MainApp = context.applicationContext as MainApp

    fun doLogin(email: String, password: String) {
        val user = UserModel(email = email, password = password)
        if (email.isNotEmpty()) {
            app.users.create(user.copy())
            i("User Added: $user")
            view.navigateToPlacemarkList()
        } else {
            view.showMessage("Please Enter an email")
        }
    }
}
