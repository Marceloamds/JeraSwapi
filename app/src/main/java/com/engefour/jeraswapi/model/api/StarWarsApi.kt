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

        //Builder do Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<StarWarsApiDef>(StarWarsApiDef::class.java)
    }

    //Função p/ fazer a requisição de todos os filmes
    fun loadMovies(): Observable<Filme>{
        return service.listMovies()
            .flatMap { filmResult -> Observable.from(filmResult.results)
                .flatMap { film -> Observable.just(Filme(
                    film.title,film.episodeId,film.openingCrawl,film.director,film.producer,
                    film.releaseDate,film.speciesUrls,film.starshipsUrls,film.vehiclesUrls,
                    film.charactersUrls,film.planetsUrls,film.url,film.creationDate,film.editedDate)) }}
    }

    //Função p/ fazer a requisição de um veículo
    fun loadVehicles(vehiclesUrls:ArrayList<String>): Observable<Veiculo>{
        return Observable.from(vehiclesUrls)
            .flatMap { vehicleUrl ->
                service.loadVehicle(Uri.parse(vehicleUrl).lastPathSegment)
            }
            .flatMap { vehicle->
                Observable.just(Veiculo(vehicle.name,vehicle.model,vehicle.vehicleClass,vehicle.manufacturer,vehicle.length,
                    vehicle.costInCredits,vehicle.crew,vehicle.passengers,vehicle.maxAtmospheringSpeed,vehicle.cargoCapacity,
                    vehicle.consumables,vehicle.filmsUrls,vehicle.pilotsUrls,vehicle.url,vehicle.creationDate,vehicle.editedDate))
            }
    }

    //Função p/ fazer a requisição de um planeta
    fun loadPlanets(planetsUrls:ArrayList<String>): Observable<Planeta> {
        return Observable.from(planetsUrls)
            .flatMap { planetUrl ->
                service.loadPlanet(Uri.parse(planetUrl).lastPathSegment)
            }
            .flatMap { planet->
                Observable.just(Planeta(planet.name,planet.diameter,planet.rotationPeriod,planet.orbitalPeriod,planet.gravity,
                    planet.population,planet.climate,planet.terrain,planet.surfaceWater,planet.residentsUrls,planet.filmsUrls,
                    planet.url,planet.creationDate,planet.editedDate))
            }
    }

    //Função p/ fazer a requisição de um personagem
    fun loadCharacters(charactersUrls:ArrayList<String>): Observable<Pessoa> {
        return Observable.from(charactersUrls)
            .flatMap { characterUrl ->
                service.loadPerson(Uri.parse(characterUrl).lastPathSegment)
            }
            .flatMap { character->
                Observable.just(Pessoa(character.name,character.birthYear,character.eyeColor,character.gender,
                    character.hairColor,character.height,character.mass,character.skinColor,character.homeworld,
                    character.filmsUrls,character.speciesUrls,character.starshipsUrls,character.vehiclesUrls,character.url,
                    character.creationDate, character.editedDate))
            }

    }
    //Função p/ fazer a requisição de uma nave
    fun loadStarships(starshipsUrls:ArrayList<String>): Observable<Nave> {
        return Observable.from(starshipsUrls)
            .flatMap { starshipUrl ->
                service.loadStarship(Uri.parse(starshipUrl).lastPathSegment)
            }
            .flatMap { starship->
                Observable.just(Nave(starship.name,starship.model,starship.starshipClass,starship.manufacturer,starship.costInCredits,
                    starship.length,starship.crew,starship.passengers,starship.maxAtmospheringSpeed,starship.hyperdriveRating,
                    starship.mglt,starship.cargoCapacity,starship.consumables,starship.filmsUrls,starship.pilotsUrls,starship.url,
                    starship.creationDate,starship.editedDate))
            }

    }
    //Função p/ fazer a requisição de uma espécie
    fun loadSpecies(speciesUrls:ArrayList<String>): Observable<Especie> {
        return Observable.from(speciesUrls)
            .flatMap { specieUrl ->
                service.loadSpecie(Uri.parse(specieUrl).lastPathSegment)
            }
            .flatMap { specie->
                Observable.just(Especie(specie.name,specie.classification,specie.designation,specie.averageHeight,
                    specie.averageLifespan,specie.eyeColors,specie.hairColors,specie.skinColors,specie.language,
                    specie.homeworld,specie.peopleUrls,specie.filmsUrls,specie.url,specie.creationDate,specie.editedDate))
            }

    }

}