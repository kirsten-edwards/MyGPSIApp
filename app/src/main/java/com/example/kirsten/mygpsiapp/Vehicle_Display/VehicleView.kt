package com.example.kirsten.mygpsiapp.Vehicle_Display

import android.arch.lifecycle.LiveData
import com.example.kirsten.mygpsiapp.Vehicle

interface VehicleView {
    fun showVehicles(vehicles: MutableList<Vehicle>)
}