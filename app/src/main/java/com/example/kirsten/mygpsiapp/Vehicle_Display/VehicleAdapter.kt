package com.example.kirsten.mygpsiapp.Vehicle_Display

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat.getColor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.kirsten.mygpsiapp.R
import com.example.kirsten.mygpsiapp.Vehicle
import kotlinx.android.synthetic.main.recyler_layout.view.*


class VehicleAdapter(private val vehicles: MutableList<Vehicle>, val mapClick: (Double, Double) -> Unit) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    var displayedVehicles = vehicles
    var query = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.recyler_layout, parent, false)))
    }

    override fun getItemCount(): Int {
        return displayedVehicles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(displayedVehicles[position])
    }

    fun updateVehicles(vehicle: List<Vehicle>) {
        this.vehicles.clear()
        this.vehicles.addAll(vehicle)
        searchVehicles(query)
    }

    fun searchVehicles(query : String){
        displayedVehicles = vehicles.asSequence().filter {
            it.label.contains(query)
        }.toMutableList()
        notifyDataSetChanged()

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vehicle: Vehicle) = with(itemView) {
            vehicleSelectedString.visibility = GONE
            recyclerString.setOnClickListener {
                vehicleSelectedString.text = vehicle.color
                if (vehicle.color.isBlank()) {
                    vehicleSelectedString.text = "No Color Listed"
                }
                if (vehicleSelectedString.visibility == VISIBLE) {
                    vehicleSelectedString.visibility = GONE
                    textviewLinkToMaps.visibility = GONE
                } else {
                    vehicleSelectedString.visibility = VISIBLE
                    textviewLinkToMaps.visibility = VISIBLE
                }
            }
            textviewLinkToMaps.setOnClickListener {
                mapClick(vehicle.latitude, vehicle.longitude)
            }
            val circleDrawable: Drawable = resources.getDrawable(R.drawable.circle, resources.newTheme())
            when (vehicle.speedIcon) {
                "red_dot" -> circleDrawable.setTint(getColor(this.context, R.color.red_dot))
                "orange_dot" -> circleDrawable.setTint(getColor(this.context, R.color.orange_dot))
                "yellow_dot" -> circleDrawable.setTint(getColor(this.context, R.color.yellow_dot))
                "bright_green_dot" -> circleDrawable.setTint(getColor(this.context, R.color.bright_green_dot))
                "green_dot" -> circleDrawable.setTint(getColor(this.context, R.color.green_dot))
                "blue_dot" -> circleDrawable.setTint(getColor(this.context, R.color.blue_dot))
                "purple_dot" -> circleDrawable.setTint(getColor(this.context, R.color.purple_dot))
                "black_dot" -> circleDrawable.setTint(getColor(this.context, R.color.black_dot))
                "" -> circleDrawable.setTint(0)
            }

            textViewVehicleLabel.text = vehicle.label
            recyclerString.text = vehicle.idWithoutCA


        }
    }
}