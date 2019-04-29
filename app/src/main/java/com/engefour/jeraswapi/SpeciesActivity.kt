package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.engefour.jeraswapi.model.api.StarWarsApi
import kotlinx.android.synthetic.main.activity_species.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SpeciesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)

        val speciesUrls = intent.getStringArrayListExtra("speciesUrls")
        val list = ArrayList<String>()
        val api = StarWarsApi()
        val specieAdapter= ArrayAdapter(
            this, android.R.layout.simple_list_item_1, ArrayList<String>())
        api.loadSpecies(speciesUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                    specie -> list.add(specie.name)
                specieAdapter.clear()
                specieAdapter.addAll(list)
                specieAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
            })

        listViewSpecies.adapter = specieAdapter
    }
}
