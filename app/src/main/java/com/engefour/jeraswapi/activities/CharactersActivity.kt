package com.engefour.jeraswapi.activities

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.engefour.jeraswapi.LoadingDialog
import com.engefour.jeraswapi.R
import com.engefour.jeraswapi.model.item.PersonItem
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_characters.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CharactersActivity : AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val charactersUrls = intent.getStringArrayListExtra("charactersUrls")
        val movieName = intent.getStringExtra("movieTitle")
        val list = ArrayList<PersonItem>()
        val api = StarWarsApi()
        val characterAdapter = GroupAdapter<ViewHolder>()

        //Muda a fonte do título
        val jediFont = Typeface.createFromAsset(assets, "fonts/Starjedi.ttf")
        textViewTitle.typeface = jediFont
        textViewTitle.text = "$movieName's Characters"

        //Prepara a lista de personagens
        listViewCharacters.layoutManager = LinearLayoutManager(this)
        listViewCharacters.adapter = characterAdapter
        listViewCharacters.isNestedScrollingEnabled = false
        listViewCharacters.isFocusable = false
        //Mostra o loading dialog
        loadingDialog = LoadingDialog(this)
        loadingDialog.showDialog()

        api.loadCharacters(charactersUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { character ->
                //onNext - quando completa uma requisição
                //Quando o primeiro item aparecer, desaparece o loading
                if(loadingDialog.isShowing()== true)
                    loadingDialog.hideDialog()
                //Cada vez que um item é adicionado, ele adiciona a lista. Feito p/ não dar error no adapter
                list.add(PersonItem(character))
                characterAdapter.clear()
                characterAdapter.addAll(list)
                characterAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                characterAdapter.notifyDataSetChanged()
                //onComplete - quando completa todas as requisições
            })

        //usa glide para carregar o background de forma eficiente
        Glide.with(this).load(R.drawable.background).centerCrop()
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    mainLayout.background = resource
                }
            })
    }
}
