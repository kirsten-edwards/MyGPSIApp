package com.example.kirsten.mygpsiapp.Login

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET("userauth/login")
    fun login(@Query("username") username: String,
              @Query("password") password: String): Single<Response<LoginResponse>>
}