package com.engefour.jeraswapi

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.engefour.jeraswapi.model.Filme
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.row_movies.view.*
import android.graphics.Typeface



class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieIntent = intent.getStringExtra("movie")
        val movie = Gson().fromJson<Filme>(movieIntent,Filme::class.java)

        val jediFont = Typeface.createFromAsset(assets, "fonts/Starjedi.ttf")
        textViewOpeningCrawl.typeface = jediFont
        textViewDirector.typeface = jediFont
        textViewProducer.typeface = jediFont
        releaseDate.typeface = jediFont
        episodeId.typeface = jediFont

        textViewTitle.text = movie.title
        openingCrawl.text = movie.openingCrawl
        textViewDirector.text = "Directed by "+movie.director
        textViewProducer.text = "Produced by "+movie.producer
        episodeId.text = movie.episodeId.toString()+"° star wars movie chronologically"


        val date = String.format("Release date: %02d/%02d/%d",
            movie.releaseDate.day,movie.releaseDate.month,movie.releaseDate.year+1900)
        releaseDate.text = date

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

        backButton.setOnClickListener {
            finish()
        }

        buttonCharacters.setOnClickListener {
            val i = Intent(this,CharactersActivity::class.java)
            i.putStringArrayListExtra("charactersUrls", movie.characters)
            i.putExtra("movieTitle",movie.title)
            startActivity(i)
        }

        buttonSpecies.setOnClickListener {
            val i = Intent(this,SpeciesActivity::class.java)
            i.putStringArrayListExtra("speciesUrls", movie.species)
            i.putExtra("movieTitle",movie.title)
            startActivity(i)
        }
        buttonPlanets.setOnClickListener {
            val i = Intent(this,PlanetsActivity::class.java)
            i.putStringArrayListExtra("planetsUrls", movie.planets)
            i.putExtra("movieTitle",movie.title)
            startActivity(i)
        }
        buttonStarships.setOnClickListener {
            val i = Intent(this,StarshipsActivity::class.java)
            i.putStringArrayListExtra("starshipsUrls", movie.starships)
            i.putExtra("movieTitle",movie.title)
            startActivity(i)
        }
        buttonVehicles.setOnClickListener {
            val i = Intent(this,VehiclesActivity::class.java)
            i.putStringArrayListExtra("vehiclesUrls", movie.vehicles)
            i.putExtra("movieTitle",movie.title)
            startActivity(i)
        }

    }
}
