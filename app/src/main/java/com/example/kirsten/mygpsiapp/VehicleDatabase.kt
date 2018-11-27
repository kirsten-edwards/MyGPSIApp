package com.example.kirsten.mygpsiapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Vehicle::class], version = 1)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun getDAO(): DAO

}