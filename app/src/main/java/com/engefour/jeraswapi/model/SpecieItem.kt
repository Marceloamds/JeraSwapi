package com.engefour.jeraswapi.model

import android.annotation.SuppressLint
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_species.view.*

//Classe do Groupie que infla a view para o listview
class SpecieItem(private val specie: Especie): Item<ViewHolder>(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewSpecieName.text = specie.name+" - "+specie.designation+" "+ specie.classification
        viewHolder.itemView.textViewHeightLifespan.text = "average "+specie.averageHeight + " height " +
                                                         "\naverage "+specie.averageLifespan+" years lifespan"
        viewHolder.itemView.textViewLanguage.text = "Speaks "+specie.language
        viewHolder.itemView.textViewSpecieColor.text =specie.eyeColors+" eyes\n"+specie.hairColors+" hair"

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
        return R.layout.row_species
    }
}