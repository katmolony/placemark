package org.wit.placemark.views.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.placemark.databinding.ActivityLoginBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import org.wit.placemark.views.placemark.PlacemarkPresenter
import org.wit.placemark.views.placemarklist.PlacemarkListView
import timber.log.Timber.i

class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var app: MainApp
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)
        i("Placemark Login/Register started..")



        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                presenter.doLogin(email, password)
            } else {
                showMessage("Please enter both email and password")
            }
        }

        binding.registerButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                presenter.doRegister(email, password)
            } else {
                showMessage("Please enter both email and password")
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
