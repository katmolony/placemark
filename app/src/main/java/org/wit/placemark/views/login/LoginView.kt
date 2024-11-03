package org.wit.placemark.views.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.placemark.databinding.ActivityLoginBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.placemarklist.PlacemarkListView
import timber.log.Timber.i

class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var user = UserModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var edit = false

        app = application as MainApp
        i("Placemark Login started..")

        binding.loginButton.setOnClickListener() {
            i("add Button Pressed")

            user.email = binding.email.text.toString()
            user.password = binding.password.text.toString()

            if (user.email.isNotEmpty()) {
                i("User Added: $user")
                app.users.create(user.copy())
                setResult(RESULT_OK)

                val launcherIntent = Intent(this, PlacemarkListView::class.java)
                startActivity(launcherIntent)

                i("Add User Button Pressed: ${user}")
            } else {
                Snackbar
                    .make(it, "Please Enter a email", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
    fun navigateToPlacemarkList() {
        val launcherIntent = Intent(this, PlacemarkListView::class.java)
        startActivity(launcherIntent)
    }
    fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}