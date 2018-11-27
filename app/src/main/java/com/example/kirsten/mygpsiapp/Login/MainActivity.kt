package com.example.kirsten.mygpsiapp.Login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.VISIBLE
import com.example.kirsten.mygpsiapp.R
import com.example.kirsten.mygpsiapp.ResourceLocator
import com.example.kirsten.mygpsiapp.Vehicle_Display.VehicleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = MainPresenter()
        presenter.bind(this)
        if (ResourceLocator.sharedPrefs?.contains("username") == true) {
            presenter.login(ResourceLocator.sharedPrefs?.getString("username", "") ?: "",
                    ResourceLocator.sharedPrefs?.getString("password", "") ?: "")
        }

        loginButton.setOnClickListener {
            presenter.login(usernameField.text.toString(), passwordField.text.toString())
        }

        ResourceLocator.sharedPrefs?.apply {
            presenter.token = getString("token", "")
        }
    }

    override fun onLoginFailure(s: String) {
        errorMessage.visibility = VISIBLE
        errorMessage.text = s
    }

    override fun onLoginSuccess(response: String) {
        ResourceLocator.sharedPrefs?.edit()?.apply {
            putString("token", response)
            if (checkBoxRememberMe.isChecked) {
                putString("username", usernameField.text.toString())
                putString("password", passwordField.text.toString())
            }
        }?.apply()
        startActivity(Intent(this, VehicleActivity::class.java))


    }

}
