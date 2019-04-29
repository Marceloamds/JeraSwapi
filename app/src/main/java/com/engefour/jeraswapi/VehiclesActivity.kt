package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.engefour.jeraswapi.model.api.StarWarsApi
import kotlinx.android.synthetic.main.activity_vehicles.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class VehiclesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)

        val vehiclesUrls = intent.getStringArrayListExtra("vehiclesUrls")
        val list = ArrayList<String>()
        val api = StarWarsApi()
        val vehicleAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, ArrayList<String>())
        api.loadVehicles(vehiclesUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                    vehicle -> list.add(vehicle.name)
                    vehicleAdapter.clear()
                    vehicleAdapter.addAll(list)
                    vehicleAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições

            })
        listViewVehicles.adapter = vehicleAdapter
    }
}
