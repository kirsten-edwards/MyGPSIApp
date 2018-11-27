package com.example.kirsten.mygpsiapp.Vehicle_Display

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.SearchView
import android.arch.lifecycle.Observer
import android.util.Log
import com.example.kirsten.mygpsiapp.*
import com.example.kirsten.mygpsiapp.Map.MapActivity
import kotlinx.android.synthetic.main.account_layout.*
import java.util.*

class VehicleActivity : AppCompatActivity(), VehicleView, SearchView.OnQueryTextListener {

    val timer = Timer()
    var dy = ResourceLocator.sharedPrefs?.getInt("dy", 0) ?: 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_layout)
        ResourceLocator.sharedPrefs?.getString("username", "")
        ResourceLocator.sharedPrefs?.getString("token", "")


        val presenter = VehiclePresenter()
        presenter.bind(this)

        searchBar.setIconifiedByDefault(false)
        searchBar.setOnQueryTextListener(this)

        timer.schedule(object : TimerTask() {
            override fun run() {
                presenter.retrieveVehicles()
            }
        }, 3000, 3000)


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

//                ResourceLocator.db.getDAO().getVehicles()
//                ResourceLocator.db.getDAO().putVehicles()
                ResourceLocator.sharedPrefs?.edit()?.apply {
                    putInt("dy", dy)
                }?.apply()
            }
        })

        val vehicleObserver = Observer<MutableList<Vehicle>> { list ->
            (recyclerView.adapter as VehicleAdapter).updateVehicles(list?.toList() ?: listOf())
            Log.d("TAG","Observed a Change")
        }

        presenter.vehicleListLive.observe(this, vehicleObserver)

        recyclerView.adapter = VehicleAdapter(mutableListOf()) { lat, long ->
            startActivity(
                    Intent(this, MapActivity::class.java)

//                    Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse("geo:0,0?q=$lat,$long")
//                    ).apply { setPackage("com.google.android.apps.maps") }
            )
        }
        accountNameDisplay.text = ResourceLocator.sharedPrefs?.getString("username", "")

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        (recyclerView.adapter as VehicleAdapter).searchVehicles(query ?: "")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        (recyclerView.adapter as VehicleAdapter).searchVehicles(newText ?: "")
        return true
    }

    override fun showVehicles(vehicles: MutableList<Vehicle>) {

        (recyclerView.adapter as VehicleAdapter).updateVehicles(vehicles)
        vehicleNumDisplay.text = vehicles.size.toString()
        if (dy != -1) {
            recyclerView.scrollY = dy
            dy = -1
        }


    }


}
