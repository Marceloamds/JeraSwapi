package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_starships.*

class StarshipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starships)

        val starshipsUrls = intent.getStringArrayListExtra("starshipsUrls")
        val movieAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, starshipsUrls)
        listViewStarships.adapter = movieAdapter
    }
}
