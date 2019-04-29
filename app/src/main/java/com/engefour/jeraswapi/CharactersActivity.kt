package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val charactersUrls = intent.getStringArrayListExtra("charactersUrls")
        val movieAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, charactersUrls)
        listViewCharacters.adapter = movieAdapter
    }
}
