package com.engefour.jeraswapi.model

import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_people.view.*
import kotlinx.android.synthetic.main.row_planets.view.*

//Classe do Groupie que infla a view para o listview
class PlanetItem(private val planet: Planeta): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewPlanetName.text = planet.name+" - "+planet.population+" habitants"
        viewHolder.itemView.textViewClimateTerrain.text = planet.climate + " climate \n"+planet.terrain+" terrain"
        viewHolder.itemView.textViewPeriods.text = planet.rotationPeriod + " hours day, "+planet.orbitalPeriod+" days year"
        viewHolder.itemView.textViewWaterGravity.text =planet.surfaceWater+"% water. Gravity: "+planet.gravity

//        val drawable =  when(movie.episodeId){
//            1-> R.drawable.thephantommenace
//            2 -> R.drawable.attackoftheclones
//            3 -> R.drawable.revengeofthesith
//            4 -> R.drawable.anewhope
//            5 -> R.drawable.empirestrikesback
//            6 -> R.drawable.returnofthejedi
//            7 -> R.drawable.theforceawakens
//            else -> R.drawable.placeholder
//        }
//        Glide.with(viewHolder.itemView).load(drawable).centerCrop().into(viewHolder.itemView.imageViewPoster)
    }
    override fun getLayout(): Int {
        return R.layout.row_planets
    }
}