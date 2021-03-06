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
import com.engefour.jeraswapi.model.item.StarshipItem
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_starships.*
import kotlinx.android.synthetic.main.activity_starships.mainLayout
import kotlinx.android.synthetic.main.activity_starships.textViewTitle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StarshipsActivity : AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starships)

        val starshipsUrls = intent.getStringArrayListExtra("starshipsUrls")
        val movieName = intent.getStringExtra("movieTitle")
        val list = ArrayList<StarshipItem>()
        val api = StarWarsApi()
        val starshipAdapter = GroupAdapter<ViewHolder>()

        //Muda a fonte do título
        val jediFont = Typeface.createFromAsset(assets, "fonts/Starjedi.ttf")
        textViewTitle.typeface = jediFont
        textViewTitle.text = "$movieName's Starships"

        //Prepara a lista de naves
        listViewStarships.layoutManager = LinearLayoutManager(this)
        listViewStarships.adapter = starshipAdapter
        listViewStarships.isNestedScrollingEnabled = false
        listViewStarships.isFocusable = false
        //Mostra o loading dialog
        loadingDialog = LoadingDialog(this)
        loadingDialog.showDialog()

        api.loadStarships(starshipsUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {starship ->
                //onNext - quando completa uma requisição
                //Quando o primeiro item aparecer, desaparece o loading
                if(loadingDialog.isShowing()== true)
                    loadingDialog.hideDialog()
                //Cada vez que um item é adicionado, ele adiciona a lista. Feito p/ não dar error no adapter
                list.add(StarshipItem(starship))
                starshipAdapter.clear()
                starshipAdapter.addAll(list)
                starshipAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
                //onComplete - quando completam todas as requisições
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
