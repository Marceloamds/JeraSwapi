package com.engefour.jeraswapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.engefour.jeraswapi.model.Filme
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.row_movies.view.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieIntent = intent.getStringExtra("movie")
        val movie = Gson().fromJson<Filme>(movieIntent,Filme::class.java)
        textViewTitle.text = movie.title
        openingCrawl.text = movie.openingCrawl
        textViewDirector.text = movie.director
        textViewProducer.text = movie.producer
        releaseDate.text = movie.releaseDate.toString()
        editedDate.text = movie.editedDate
        episodeId.text = movie.episodeId.toString()
        val drawable =  when(movie.episodeId){

            1-> R.drawable.thephantommenace
            2 -> R.drawable.attackoftheclones
            3 -> R.drawable.revengeofthesith
            4 -> R.drawable.anewhope
            5 -> R.drawable.empirestrikesback
            6 -> R.drawable.returnofthejedi
            7 -> R.drawable.theforceawakens
            else -> R.drawable.placeholder
        }

        Glide.with(this).load(drawable).centerCrop().into(imageViewPoster)

        buttonCharacters.setOnClickListener {
            val i = Intent(this,CharactersActivity::class.java)
            i.putStringArrayListExtra("charactersUrls", movie.characters)
            startActivity(i)
        }

        buttonSpecies.setOnClickListener {
            val i = Intent(this,SpeciesActivity::class.java)
            i.putStringArrayListExtra("speciesUrls", movie.species)
            startActivity(i)
        }
        buttonPlanets.setOnClickListener {
            val i = Intent(this,PlanetsActivity::class.java)
            i.putStringArrayListExtra("planetsUrls", movie.planets)
            startActivity(i)
        }
        buttonStarships.setOnClickListener {
            val i = Intent(this,StarshipsActivity::class.java)
            i.putStringArrayListExtra("starshipsUrls", movie.starships)
            startActivity(i)
        }
        buttonVehicles.setOnClickListener {
            val i = Intent(this,VehiclesActivity::class.java)
            i.putStringArrayListExtra("vehiclesUrls", movie.vehicles)
            startActivity(i)
        }

    }
}
