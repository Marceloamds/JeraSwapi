package com.engefour.jeraswapi.model

import com.bumptech.glide.Glide
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_planets.view.*

//Classe do Groupie que infla a view para o listview
class PlanetItem(private val planet: Planeta): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewPlanetName.text = planet.name+" - "+planet.population+" habitants"
        viewHolder.itemView.textViewClimateTerrain.text = planet.climate + " climate \n"+planet.terrain+" terrain"
        viewHolder.itemView.textViewPeriods.text = planet.rotationPeriod + " hours day, "+planet.orbitalPeriod+" days year"
        viewHolder.itemView.textViewWaterGravity.text =planet.surfaceWater+"% water. Gravity: "+planet.gravity

        val drawable =  when(planet.name){
            "Tatooine" -> "https://ogimg.infoglobo.com.br/cultura/12150764-094-506/FT1086A/652/xtattoine.jpg.pagespeed.ic.UDI9IRCDfU.jpg"
            "Alderaan" -> "https://lumiere-a.akamaihd.net/v1/images/databank_alderaan_01_169_4a5264e2.jpeg?region=0%2C0%2C1560%2C878&width=960"
            "Hoth" -> "http://cdn.collider.com/wp-content/uploads/star-wars-hoth-empire-strikes-back.jpg"
            "Bespin" -> "https://data4.origin.com/content/dam/originx/web/app/games/bastion/Screenshots/SWBF_dlc_screenhi_930x524_en_US_bespin_cloud5_v1.jpg"
            "Yavin" ->"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Star_Wars_in_Guatemala_3.jpg/290px-Star_Wars_in_Guatemala_3.jpg"
            "Naboo" -> "https://lumiere-a.akamaihd.net/v1/images/databank_naboo_01_169_6cd7e1e0.jpeg?region=0%2C0%2C1560%2C878&width=960"
            else -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7S-9K8IqBWCUy-tUEi-F4eRIU9wfnMWhKHEPiAzZ-UvESDUJa"
        }
        Glide.with(viewHolder.itemView).load(drawable).centerCrop().into(viewHolder.itemView.imageViewPlanet)
    }
    override fun getLayout(): Int {
        return R.layout.row_planets
    }
}