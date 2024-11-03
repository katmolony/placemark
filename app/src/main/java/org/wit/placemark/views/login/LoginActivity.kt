package org.wit.placemark.views.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import org.wit.placemark.R
import org.wit.placemark.databinding.ActivityLoginBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.placemarklist.PlacemarkListView
import timber.log.Timber
import timber.log.Timber.i

class LoginActivity : AppCompatActivity() {

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
}