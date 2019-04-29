package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.engefour.jeraswapi.model.api.StarWarsApi
import kotlinx.android.synthetic.main.activity_starships.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StarshipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starships)

        val starshipsUrls = intent.getStringArrayListExtra("starshipsUrls")
        val list = ArrayList<String>()
        val api = StarWarsApi()
        val starshipAdapter= ArrayAdapter(
            this, android.R.layout.simple_list_item_1, ArrayList<String>())
        api.loadStarships(starshipsUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                    starship -> list.add(starship.name)
                starshipAdapter.clear()
                starshipAdapter.addAll(list)
                starshipAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
            })

        listViewStarships.adapter = starshipAdapter
    }
}
