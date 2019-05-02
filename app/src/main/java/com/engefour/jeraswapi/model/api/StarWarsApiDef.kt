package com.engefour.jeraswapi.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

//função que faz a definição da API. Faz a requisição do Retrofit e retorna um Observable com RxJava
interface StarWarsApiDef{
    @GET("films")
    fun listMovies(): Observable<FilmResult>

    @GET("species/{specieId}")
    fun loadSpecie(@Path("specieId") personId:String) : Observable<Specie>

    @GET("starships/{starshipId}")
    fun loadStarship(@Path("starshipId") starshipId:String) : Observable<Starship>

    @GET("vehicles/{vehicleId}")
    fun loadVehicle(@Path("vehicleId") vehicleId:String) : Observable<Vehicle>

    @GET("people/{personId}")
    fun loadPerson(@Path("personId") personId:String) : Observable<Person>

    @GET("planets/{planetId}")
    fun loadPlanet(@Path("planetId") planetId:String) : Observable<Planet>
}