package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_planets.*

class PlanetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets)

        val planetsUrls = intent.getStringArrayListExtra("planetsUrls")
        val movieAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, planetsUrls)
        listViewPlanets.adapter = movieAdapter
    }
}
