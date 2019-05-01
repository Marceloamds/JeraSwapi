package com.engefour.jeraswapi.model

import android.annotation.SuppressLint
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_species.view.*
import kotlinx.android.synthetic.main.row_starships.view.*

//Classe do Groupie que infla a view para o listview
class StarshipItem(private val starship: Nave): Item<ViewHolder>(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewStarshipName.text = starship.name+"\n"+starship.model
        viewHolder.itemView.textViewStarshipClass.text = starship.starshipClass+"\n\nMade by "+ starship.manufacturer
        viewHolder.itemView.textViewCrewConsumable.text = "Transports "+starship.crew+" crew members\nAnd "+starship.passengers+" passengers"+
                "\nWith "+starship.consumables+" worth of supplies"
        viewHolder.itemView.textViewLengthCost.text = starship.length+" meters in length\nCosts "+starship.costInCredits+" credits"
        viewHolder.itemView.textViewStarshipSpeed.text ="Speed: "+starship.mglt+" per hour \nHyperdrive Rating: "+
                starship.hyperdriveRating+"\nMax Atmosphering Speed: "+starship.maxAtmospheringSpeed

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
        return R.layout.row_starships
    }
}