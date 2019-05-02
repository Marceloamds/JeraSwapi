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
import com.engefour.jeraswapi.model.item.PlanetItem
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_planets.*
import kotlinx.android.synthetic.main.activity_planets.mainLayout
import kotlinx.android.synthetic.main.activity_planets.textViewTitle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PlanetsActivity : AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets)

        val planetsUrls = intent.getStringArrayListExtra("planetsUrls")
        val movieName = intent.getStringExtra("movieTitle")
        val list = ArrayList<PlanetItem>()
        val api = StarWarsApi()
        val planetAdapter = GroupAdapter<ViewHolder>()

        //Muda a fonte do título
        val jediFont = Typeface.createFromAsset(assets, "fonts/Starjedi.ttf")
        textViewTitle.typeface = jediFont
        textViewTitle.text = "$movieName's Planets"

        //Prepara a lista de planetas
        listViewPlanets.layoutManager = LinearLayoutManager(this)
        listViewPlanets.adapter = planetAdapter
        listViewPlanets.isNestedScrollingEnabled = false
        listViewPlanets.isFocusable = false
        //Mostra o loading dialog
        loadingDialog = LoadingDialog(this)
        loadingDialog.showDialog()

        api.loadPlanets(planetsUrls)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { planet ->
                //onNext - quando completa uma requisição
                //Quando o primeiro item aparecer, desaparece o loading
                if(loadingDialog.isShowing()== true)
                    loadingDialog.hideDialog()
                //Cada vez que um item é adicionado, ele adiciona a lista. Feito p/ não dar error no adapter
                list.add(PlanetItem(planet))
                planetAdapter.clear()
                planetAdapter.addAll(list)
                planetAdapter.notifyDataSetChanged()
            },{
                //onError - quando dá erro na requisição
                    e -> e.printStackTrace()
            },{
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
