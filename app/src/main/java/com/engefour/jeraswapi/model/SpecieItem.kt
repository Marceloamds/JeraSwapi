package com.engefour.jeraswapi.model

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_species.view.*

//Classe do Groupie que infla a view para o listview
class SpecieItem(private val specie: Especie): Item<ViewHolder>(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewSpecieName.text = specie.name+" - "+specie.designation+" "+ specie.classification
        viewHolder.itemView.textViewHeightLifespan.text = "Average "+specie.averageHeight + " height " +
                                                         "\nAverage "+specie.averageLifespan+" years lifespan"
        viewHolder.itemView.textViewLanguage.text = "Speaks "+specie.language
        viewHolder.itemView.textViewSpecieColor.text =specie.eyeColors+" eyes\n"+specie.hairColors+" hair"

        val drawable =  when(specie.name){
            "Human"-> "https://secure.img1-fg.wfcdn.com/im/75413820/resize-h600-w600%5Ecompr-r85/2804/28041725/%27Proportions+of+the+Human+Figure+%28Vitruvian+Man%29%27+by+Leonardo+Da+Vinci+Graphic+Art+on+Wrapped+Canvas.jpg"
            "Droid" ->"https://images-na.ssl-images-amazon.com/images/I/613XhZOh78L._SY450_.jpg"
            "Yoda's Species" -> "https://img1.looper.com/img/gallery/the-worst-things-yoda-has-ever-done/intro-1523455063.jpg"
            "Wookie" -> "https://i.ebayimg.com/images/g/Bg4AAOxy4dNS0rUD/s-l300.jpg"
            else -> "https://image.freepik.com/free-icon/alien_318-1528.jpg"
        }
        Glide.with(viewHolder.itemView).load(drawable).centerCrop().into(viewHolder.itemView.imageViewSpecie)
    }
    override fun getLayout(): Int {
        return R.layout.row_species
    }
}