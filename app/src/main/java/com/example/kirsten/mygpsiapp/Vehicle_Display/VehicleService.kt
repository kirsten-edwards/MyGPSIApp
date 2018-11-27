package com.example.kirsten.mygpsiapp.Vehicle_Display

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleService {
    @GET("vehicle/location")
    fun vehicles(@Query("session") session: String,
                 @Query("extended") extended: Boolean = true,
                 @Query("channel") channel: String = "gpsi_android_app"
    ): Single<Response<VehicleResponse>>
}