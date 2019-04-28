package com.engefour.jeraswapi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_movies.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.recyclerview.widget.GridLayoutManager



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAdapter = GroupAdapter<ViewHolder>()
        listMovies.adapter = movieAdapter
        listMovies.layoutManager = GridLayoutManager(this, 2)

        val api = StarWarsApi()
        api.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                movie -> movieAdapter.add(MovieItem(movie.title,movie.episodeId))
            },{
                e -> e.printStackTrace()
            },{
                movieAdapter.notifyDataSetChanged()
            })

    }

    //Classe do Groupie que infla a view para o listview
    class MovieItem(private val movieTitle:String, private val episodeId:Int): Item<ViewHolder>(){
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textViewTitle.text = movieTitle
            viewHolder.itemView.setOnClickListener {
//                val i = Intent(context,MovieActivity::class.java)
//                i.putExtra("userId", userId)
//                context?.startActivity(i)
            }
        }
        override fun getLayout(): Int {
            return R.layout.row_movies
        }

        override fun getSpanSize(spanCount: Int, position: Int) = spanCount/2
    }
}


