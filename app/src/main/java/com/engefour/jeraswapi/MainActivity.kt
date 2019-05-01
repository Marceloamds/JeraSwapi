package com.engefour.jeraswapi

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_movies.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.engefour.jeraswapi.model.Filme
import com.google.gson.Gson
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAdapter = GroupAdapter<ViewHolder>()
        listMovies.adapter = movieAdapter
        listMovies.layoutManager = LinearLayoutManager(this)
        listMovies.isNestedScrollingEnabled = false
        listMovies.isFocusable = false

        Glide.with(this).load(R.drawable.background).centerCrop()
            .into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                mainLayout.background = resource
            }
        })
        Glide.with(this).load(R.drawable.logo).into(imageViewLogo)

        val api = StarWarsApi()
        api.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                //onNext - quando completa uma requisição
                movie -> movieAdapter.add(MovieItem(this,movie))
            },{
                //onError - quando dá erro na requisição
                e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
                movieAdapter.notifyDataSetChanged()
                Glide.with(this).load(R.drawable.background).into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        mainLayout.background = resource
                    }
                })
            })

    }

    //Classe do Groupie que infla a view para o listview
    class MovieItem(private val context: Context,private val movie: Filme): Item<ViewHolder>(){
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textViewTitle.text = movie.title
            val yearRelease = 1900+ movie.releaseDate.year
            viewHolder.itemView.textViewDate.text = "Released in $yearRelease"
            viewHolder.itemView.textViewDirector.text = "Directed by "+movie.director
            viewHolder.itemView.setOnClickListener {
                val i = Intent(context,MovieDetailActivity::class.java)
                i.putExtra("movie", Gson().toJson(movie))
                context.startActivity(i)
            }
            
            viewHolder.itemView.buttonSinopse.setOnClickListener {
                //Chama o popup de troca e acessa os elementos
                val dialog = Dialog(context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCanceledOnTouchOutside(true)
                val view = LayoutInflater.from(context).inflate(R.layout.popup_opening,null)
                val textViewOpening = view.findViewById<TextView>(R.id.textViewOpening)
                val textViewOpeningTitle = view.findViewById<TextView>(R.id.textViewOpeningTitle)
                textViewOpening.text = movie.openingCrawl
                textViewOpeningTitle.text = movie.title
                dialog.setContentView(view)
                dialog.show()
            }

            val drawable =  when(movie.episodeId){
                1-> R.drawable.thephantommenace
                2 -> R.drawable.attackoftheclones
                3 -> R.drawable.revengeofthesith
                4 -> R.drawable.anewhope
                5 -> R.drawable.empirestrikesback
                6 -> R.drawable.returnofthejedi
                7 -> R.drawable.theforceawakens
                else -> R.drawable.placeholder
            }
            Glide.with(viewHolder.itemView).load(drawable).centerCrop().into(viewHolder.itemView.imageViewPoster)
        }
        override fun getLayout(): Int {
            return R.layout.row_movies
        }
    }
}


