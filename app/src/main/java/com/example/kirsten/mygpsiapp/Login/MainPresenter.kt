package com.example.kirsten.mygpsiapp.Login

import android.annotation.SuppressLint
import com.example.kirsten.mygpsiapp.ResourceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter {


    var view: MainView? = null
    fun bind(mainView: MainView) {
        view = mainView

    }

    var token: String = ""

    @SuppressLint("CheckResult")
    fun login(username: String, password: String) {

        ResourceLocator.loginService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        token = response.body()?.data?.token ?: "No Token Found"
                        view?.onLoginSuccess(token)
                    } else {
                        view?.onLoginFailure("Login Failed!${response.code()}")
                    }
                }, {
                    view?.onLoginFailure(it.message ?: "Super Fail")
                })
    }
}
