package com.engefour.jeraswapi

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.engefour.jeraswapi.model.SpecieItem
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_species.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SpeciesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        val speciesUrls = intent.getStringArrayListExtra("speciesUrls")
        val movieName = intent.getStringExtra("movieTitle")
        val list = ArrayList<SpecieItem>()
        val api = StarWarsApi()
        val specieAdapter = GroupAdapter<ViewHolder>()

        val jediFont = Typeface.createFromAsset(assets, "fonts/Starjedi.ttf")
        textViewTitle.typeface = jediFont
        textViewTitle.text = "$movieName's Species"

        listViewSpecies.layoutManager = LinearLayoutManager(this)
        listViewSpecies.adapter = specieAdapter
        listViewSpecies.isNestedScrollingEnabled = false
        listViewSpecies.isFocusable = false

        api.loadSpecies(speciesUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                specie -> list.add(SpecieItem(specie))
                specieAdapter.clear()
                specieAdapter.addAll(list)
                specieAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
            })

        Glide.with(this).load(R.drawable.background).centerCrop()
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    mainLayout.background = resource
                }
            })
    }
}
