package com.example.kirsten.mygpsiapp.Login

interface MainView {
    fun onLoginSuccess(response: String)
    fun onLoginFailure(s: String)
}