package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.engefour.jeraswapi.model.Filme
import com.google.gson.Gson
import kotlinx.android.synthetic.main.row_movies.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val movieIntent = intent.getStringExtra("movie")
        val movie = Gson().fromJson<Filme>(movieIntent,Filme::class.java)
        textViewTitle.text = movie.title
    }
}
