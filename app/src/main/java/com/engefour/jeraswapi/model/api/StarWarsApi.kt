package com.engefour.jeraswapi.model.api

import android.net.Uri
import com.engefour.jeraswapi.model.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class StarWarsApi{
    val service: StarWarsApiDef

    init{
        //logging é opcional. Serve pra pegar o log do que tá acontecendo com o servidor
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<StarWarsApiDef>(StarWarsApiDef::class.java)
    }

    fun loadMovies(): Observable<Filme>{
        return service.listMovies()
            .flatMap { filmResult -> Observable.from(filmResult.results)
                .flatMap { film -> Observable.just(Filme(
                    film.title,film.episodeId,film.openingCrawl,film.director,film.producer,
                    film.releaseDate,ArrayList<Especie>(),ArrayList<Nave>(),ArrayList<Veiculo>(),
                    ArrayList<Pessoa>(),ArrayList<Planeta>(),film.url,film.creationDate,film.editedDate)) }}
    }

    fun loadMoviesFull() : Observable<Filme>{
        return service.listMovies()
            .flatMap { filmResult -> Observable.from(filmResult.results)
                .flatMap { film ->
                    Observable.zip(
                        Observable.just(Filme(
                    film.title,film.episodeId,film.openingCrawl,film.director,film.producer,
                    film.releaseDate,ArrayList<Especie>(),ArrayList<Nave>(),ArrayList<Veiculo>(),
                    ArrayList<Pessoa>(),ArrayList<Planeta>(),film.url,film.creationDate,film.editedDate)),
                        Observable.from(film.charactersUrls)
                            .flatMap {personUrl ->
                                service.loadPerson(Uri.parse(personUrl).lastPathSegment)
                            }
                            .flatMap { person ->
                                Observable.just(Pessoa(person.name,person.birthYear,person.eyeColor,person.gender,
                                    person.hairColor,person.height,person.mass,person.skinColor,person.homeworld,
                                    ArrayList<Filme>(),ArrayList<Especie>(),ArrayList<Nave>(),ArrayList<Veiculo>(),
                                    person.url,person.creationDate,person.editedDate))
                            }
                            .toList()
                    ) { movie, people ->
                        movie.characters.addAll(people)
                        movie
                    }
                }}
    }
}