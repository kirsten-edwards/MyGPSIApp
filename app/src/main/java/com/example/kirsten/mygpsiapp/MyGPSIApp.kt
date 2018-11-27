package com.example.kirsten.mygpsiapp

import android.app.Application

class MyGPSIApp : Application(){
    override fun onCreate() {
        super.onCreate()
        ResourceLocator.bindContext(this)
    }
}