package com.example.kirsten.mygpsiapp

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
@Entity
class Vehicle(
        @PrimaryKey
        var id: String = "",
        var label: String = "",
        var vin: String = "",
        var color: String = "",
        var latitude: Double = 0.0,
        var longitude: Double = 0.0,
        @field:Json(name = "speed_icon") var speedIcon: String = ""

) {
    val idWithoutCA: String
        get() {
            if (id.toLowerCase()[0].isLetter()) {
                return id.substring(2)
            }
            return id
        }

}
