package com.engefour.jeraswapi.model

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import com.bumptech.glide.Glide
import com.engefour.jeraswapi.MovieDetailActivity
import com.engefour.jeraswapi.R
import com.google.gson.Gson
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_movies.view.*

//Classe do Groupie que infla a view para o listview
class MovieItem(private val context: Context, private val movie: Filme): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textViewTitle.text = movie.title
        val yearRelease = 1900+ movie.releaseDate.year
        viewHolder.itemView.textViewDate.text = "Released in $yearRelease"
        viewHolder.itemView.textViewDirector.text = "Directed by "+movie.director
        viewHolder.itemView.setOnClickListener {
            val i = Intent(context, MovieDetailActivity::class.java)
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