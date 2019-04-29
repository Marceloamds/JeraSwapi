package com.engefour.jeraswapi

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
import androidx.recyclerview.widget.GridLayoutManager
import com.engefour.jeraswapi.model.Filme
import com.google.gson.Gson


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
                //onNext - quando completa uma requisição
                movie -> movieAdapter.add(MovieItem(this,movie))
            },{
                //onError - quando dá erro na requisição
                e -> e.printStackTrace()
            },{
                //onComplete - quando completa todas as requisições
                movieAdapter.notifyDataSetChanged()
            })

    }

    //Classe do Groupie que infla a view para o listview
    class MovieItem(private val context: Context,private val movie: Filme): Item<ViewHolder>(){
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textViewTitle.text = movie.title
            viewHolder.itemView.setOnClickListener {
                val i = Intent(context,MovieActivity::class.java)
                i.putExtra("movie", Gson().toJson(movie))
                context.startActivity(i)
            }
        }
        override fun getLayout(): Int {
            return R.layout.row_movies
        }

        override fun getSpanSize(spanCount: Int, position: Int) = spanCount/2
    }
}


