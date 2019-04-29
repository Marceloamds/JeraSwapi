package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.engefour.jeraswapi.model.api.StarWarsApi
import kotlinx.android.synthetic.main.activity_planets.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PlanetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets)

        val planetsUrls = intent.getStringArrayListExtra("planetsUrls")
        val list = ArrayList<String>()
        val api = StarWarsApi()
        val planetAdapter= ArrayAdapter(
            this, android.R.layout.simple_list_item_1, ArrayList<String>())
        api.loadPlanets(planetsUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                    planet -> list.add(planet.name)
                    planetAdapter.clear()
                    planetAdapter.addAll(list)
                    planetAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
            })

        listViewPlanets.adapter = planetAdapter
    }
}
