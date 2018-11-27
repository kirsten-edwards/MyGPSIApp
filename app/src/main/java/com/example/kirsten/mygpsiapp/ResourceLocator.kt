package com.example.kirsten.mygpsiapp

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.kirsten.mygpsiapp.Constants.SHARE_PREFERENCE_KEY
import com.example.kirsten.mygpsiapp.Login.LoginService
import com.example.kirsten.mygpsiapp.Vehicle_Display.VehicleService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ResourceLocator {
    var db : VehicleDatabase? = null
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.gpsinsight.com/v2/")
            .addConverterFactory((MoshiConverterFactory.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val loginService = retrofit.create(LoginService::class.java)
    val vehicleService = retrofit.create(VehicleService::class.java)

    var sharedPrefs: SharedPreferences? = null

    fun bindContext(context: Context) {
        sharedPrefs = context.getSharedPreferences(SHARE_PREFERENCE_KEY, Context.MODE_PRIVATE)
        db = Room.databaseBuilder(context,VehicleDatabase::class.java, "database").build()

    }



}
