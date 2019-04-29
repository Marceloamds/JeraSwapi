package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_vehicles.*

class VehiclesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)

        val vehiclesUrls = intent.getStringArrayListExtra("vehiclesUrls")
        val movieAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, vehiclesUrls)
        listViewVehicles.adapter = movieAdapter
    }
}
