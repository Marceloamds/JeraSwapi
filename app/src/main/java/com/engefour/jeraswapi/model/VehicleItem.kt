package com.engefour.jeraswapi.model

import android.annotation.SuppressLint
import com.engefour.jeraswapi.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_vehicles.view.*

//Classe do Groupie que infla a view para o listview
class VehicleItem(private val vehicle: Veiculo): Item<ViewHolder>(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewVehicleName.text = vehicle.name+"\n"+vehicle.model
        viewHolder.itemView.textViewVehicleClass.text = vehicle.vehicleClass+" vehicle\nMade by "+ vehicle.manufacturer
        viewHolder.itemView.textViewCrewConsumable.text = "Transports "+vehicle.crew+" crew members\nand "+vehicle.passengers+" passengers"+
                "\nWith "+vehicle.consumables+" worth of supplies\nand "+vehicle.cargoCapacity+" kilos of cargo"
        viewHolder.itemView.textViewLengthCost.text = vehicle.length+" meters in length\nCosts "+vehicle.costInCredits+" credits"

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
        return R.layout.row_vehicles
    }
}