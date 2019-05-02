package com.engefour.jeraswapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.engefour.jeraswapi.model.api.StarWarsApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.engefour.jeraswapi.LoadingDialog
import com.engefour.jeraswapi.R
import com.engefour.jeraswapi.model.item.MovieItem


class MainActivity : AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog

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
        loadingDialog = LoadingDialog(this)
        loadingDialog.showDialog()

        val api = StarWarsApi()
        api.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {movie ->
            if(loadingDialog.isShowing()== true)
                    loadingDialog.hideDialog()
                //onNext - quando completa uma requisição
                movieAdapter.add(MovieItem(this, movie))
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

}


