package com.engefour.jeraswapi.model

import android.content.Context
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_people.view.*

//Classe do Groupie que infla a view para o listview
class PersonItem(private val context: Context, private val person: Pessoa): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewPersonName.text = person.name
        viewHolder.itemView.textViewBornDate.text = "Born in "+person.birthYear
        viewHolder.itemView.textViewHeightMass.text = person.height+" cm height ,"+person.mass+" kg"
        viewHolder.itemView.textViewPersonColor.text = person.skinColor+" skin, "+person.hairColor+" hair, "+person.eyeColor+" eyes"
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
        return R.layout.row_people
    }
}