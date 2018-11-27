package com.example.kirsten.mygpsiapp.Vehicle_Display

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kirsten.mygpsiapp.ResourceLocator
import com.example.kirsten.mygpsiapp.ResourceLocator.db
import com.example.kirsten.mygpsiapp.Vehicle
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class VehiclePresenter : ViewModel() {

    var view: VehicleView? = null
    val service = ResourceLocator.vehicleService

    var vehicleListLive : LiveData<MutableList<Vehicle>>


    init {
        vehicleListLive = ResourceLocator.db?.getDAO()?.getVehicles()!!
    }

    @SuppressLint("CheckResult")
    fun bind(vehicleView: VehicleView) {
        view = vehicleView
//        Single.fromCallable { ResourceLocator.db?.getDAO()?.getVehicles() }
//                .subscribeOn(Schedulers.io())
//                .subscribe { vehicles ->
//                    Handler(Looper.getMainLooper()).post {
//                        view?.showVehicles(vehicles?.toMutableList() ?: mutableListOf())
//                    }
//                }
    }

    @SuppressLint("CheckResult")
    private fun getVehicles() {
        service.vehicles(ResourceLocator.sharedPrefs?.getString("token", "") ?: "")
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        ResourceLocator.db?.getDAO()?.putVehicles(response?.body()?.data?.toList()
                                ?: listOf())
//                        val vehicles = ResourceLocator.db?.getDAO()?.getVehicles()
//                        Handler(Looper.getMainLooper()).post {
//                            view?.showVehicles(vehicles?.toMutableList() ?: mutableListOf())
//                        }

                    } else {
                        Log.d("error", "${response.errorBody()?.string()}, ${response.code()}")
                    }

                }, { e -> Log.e("TAG", "$e") }
                )

    }


    fun retrieveVehicles() {
        getVehicles()
    }


}

