package com.example.kirsten.mygpsiapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*

@Dao
interface DAO {
    @Query ("SELECT * FROM vehicle")
    fun getVehicles(): LiveData<MutableList<Vehicle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putVehicles(vehicles: List<Vehicle>)


}