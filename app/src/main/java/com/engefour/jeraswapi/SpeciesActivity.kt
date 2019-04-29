package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_species.*

class SpeciesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        val speciesUrls = intent.getStringArrayListExtra("speciesUrls")
        val movieAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, speciesUrls)
        listViewSpecies.adapter = movieAdapter
    }
}
