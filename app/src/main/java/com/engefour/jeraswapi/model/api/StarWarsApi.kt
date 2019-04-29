package com.engefour.jeraswapi.model.api

import android.net.Uri
import android.util.Log
import com.engefour.jeraswapi.model.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.concurrent.TimeUnit

class StarWarsApi{
    private val service: StarWarsApiDef

    init{
        //logging é opcional. Serve pra pegar o log do que tá acontecendo com o servidor
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES) // read timeout

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
                    film.releaseDate,film.speciesUrls,film.starshipsUrls,film.vehiclesUrls,
                    film.charactersUrls,film.planetsUrls,film.url,film.creationDate,film.editedDate)) }}
    }

    fun loadVehicles(vehiclesUrls:ArrayList<String>): Observable<Veiculo>{
        return Observable.from(vehiclesUrls)
            .flatMap { vehicleUrl ->
                Log.d("lala1",vehicleUrl)
                service.loadVehicle(Uri.parse(vehicleUrl).lastPathSegment)
            }
            .flatMap { vehicle->
                Observable.just(Veiculo(vehicle.name,vehicle.model,vehicle.vehicleClass,vehicle.manufacturer,vehicle.length,
                    vehicle.costInCredits,vehicle.crew,vehicle.passengers,vehicle.maxAtmospheringSpeed,vehicle.cargoCapacity,
                    vehicle.consumables,vehicle.filmsUrls,vehicle.pilotsUrls,vehicle.url,vehicle.creationDate,vehicle.editedDate))
            }
    }

    fun loadMoviesFull() : Observable<Filme>{
        return service.listMovies()
            .flatMap { filmResult -> Observable.from(filmResult.results)
                .flatMap { film ->
                    Observable.zip(
                        Observable.just(Filme(
                    film.title,film.episodeId,film.openingCrawl,film.director,film.producer,
                    film.releaseDate,ArrayList<String>(),ArrayList<String>(),ArrayList<String>(),
                    ArrayList<String>(),ArrayList<String>(),film.url,film.creationDate,film.editedDate)),
                        Observable.from(film.charactersUrls)
                            .flatMap {personUrl ->
                                service.loadPerson(Uri.parse(personUrl).lastPathSegment)
                            }
                            .flatMap { person ->
                                Observable.just(Pessoa(person.name,person.birthYear,person.eyeColor,person.gender,
                                    person.hairColor,person.height,person.mass,person.skinColor,person.homeworld,
                                    ArrayList<String>(),ArrayList<String>(),ArrayList<String>(),ArrayList<String>(),
                                    person.url,person.creationDate,person.editedDate))
                            }
                            .toList()
                    ) { movie, people ->
//                        movie.characters.addAll(people)
                        movie
                    }
                }}
    }
}