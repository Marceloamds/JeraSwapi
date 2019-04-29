package com.engefour.jeraswapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.engefour.jeraswapi.model.api.StarWarsApi
import kotlinx.android.synthetic.main.activity_characters.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val charactersUrls = intent.getStringArrayListExtra("charactersUrls")
        val list = ArrayList<String>()
        val api = StarWarsApi()
        val characterAdapter= ArrayAdapter(
            this, android.R.layout.simple_list_item_1,  ArrayList<String>())
        api.loadCharacters(charactersUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { character ->
            //onNext - quando completa uma requisição
                list.add(character.name)
                characterAdapter.clear()
                characterAdapter.addAll(list)
                characterAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
            })

        listViewCharacters.adapter = characterAdapter
    }
}
