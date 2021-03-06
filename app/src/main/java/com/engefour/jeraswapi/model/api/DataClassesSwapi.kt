package com.engefour.jeraswapi.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

//Data classes da API para serem usadas na requisição ao swapi
//Todas as variáveis estão com o mesmo nome da API

data class FilmResult(val results: List<Film>)

data class  Film(val title:String,
                 @SerializedName("episode_id") val episodeId:Int,
                 @SerializedName("opening_crawl") val openingCrawl:String,
                 val director:String,
                 val producer:String,
                 @SerializedName("release_date") val releaseDate:Date,
                 @SerializedName("species") val speciesUrls: ArrayList<String>,
                 @SerializedName("starships") val starshipsUrls: ArrayList<String>,
                 @SerializedName("vehicles") val vehiclesUrls: ArrayList<String>,
                 @SerializedName("characters") val charactersUrls: ArrayList<String>,
                 @SerializedName("planets") val planetsUrls: ArrayList<String>,
                 val url: String,
                 @SerializedName("created") val creationDate: String,
                 @SerializedName("edited") val editedDate: String)

data class  Specie( val name:String,
                    val classification:String,
                    val designation:String,
                    @SerializedName("average_height") val averageHeight:String,
                    @SerializedName("average_lifespan") val averageLifespan:String,
                    @SerializedName("eye_colors") val eyeColors: String,
                    @SerializedName("hair_colors") val hairColors: String,
                    @SerializedName("skin_colors") val skinColors: String,
                    val language: String,
                    val homeworld: String,
                    @SerializedName("people") val peopleUrls: ArrayList<String>,
                    @SerializedName("films") val filmsUrls: ArrayList<String>,
                    val url: String,
                    @SerializedName("created") val creationDate: String,
                    @SerializedName("edited") val editedDate: String)

data class  Starship( val name:String,
                      val model: String,
                      @SerializedName("starship_class") val starshipClass: String,
                      val manufacturer: String,
                      @SerializedName("cost_in_credits") val costInCredits: String,
                      val length:String,
                      val crew: String,
                      val passengers: String,
                      @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
                      @SerializedName("hyperdrive_rating") val hyperdriveRating: String,
                      @SerializedName("MGLT") val mglt: String,
                      @SerializedName("cargo_capacity") val cargoCapacity: String,
                      val consumables: String,
                      @SerializedName("films") val filmsUrls: ArrayList<String>,
                      @SerializedName("pilots") val pilotsUrls: ArrayList<String>,
                      val url: String,
                      @SerializedName("created") val creationDate: String,
                      @SerializedName("edited ") val editedDate: String)

data class  Vehicle( val name:String,
                     val model:String,
                     @SerializedName("vehicle_class") val vehicleClass:String,
                     val manufacturer:String,
                     val length:String,
                     @SerializedName("cost_in_credits") val costInCredits:String,
                     val crew: String,
                     val passengers: String,
                     @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
                     @SerializedName("cargo_capacity") val cargoCapacity: String,
                     @SerializedName("consumables") val consumables: String,
                     @SerializedName("films") val filmsUrls: ArrayList<String>,
                     @SerializedName("pilots ") val pilotsUrls: ArrayList<String>,
                     val url: String,
                     @SerializedName("created") val creationDate: String,
                     @SerializedName("edited") val editedDate: String)

data class  Person( val name:String,
                       @SerializedName("birth_year") val birthYear:String,
                       @SerializedName("eye_color") val eyeColor:String,
                       val gender:String,
                       @SerializedName("hair_color") val hairColor: String,
                       val height: String,
                       val mass: String,
                       @SerializedName("skin_color") val skinColor: String,
                       val homeworld: String,
                       @SerializedName("films") val filmsUrls: ArrayList<String>,
                       @SerializedName("species") val speciesUrls: ArrayList<String>,
                       @SerializedName("starships") val starshipsUrls: ArrayList<String>,
                       @SerializedName("vehicles ") val vehiclesUrls: ArrayList<String>,
                       val url: String,
                       @SerializedName("created") val creationDate: String,
                       @SerializedName("edited") val editedDate: String)

data class  Planet( val name:String,
                    val diameter:String,
                    @SerializedName("rotation_period") val rotationPeriod:String,
                    @SerializedName("orbital_period") val orbitalPeriod:String,
                    val gravity:String,
                    val population: String,
                    val climate: String,
                    val terrain: String,
                    @SerializedName("surface_water") val surfaceWater: String,
                    @SerializedName("residents") val residentsUrls: ArrayList<String>,
                    @SerializedName("films") val filmsUrls: ArrayList<String>,
                    val url: String,
                    @SerializedName("created") val creationDate: String,
                    @SerializedName("edited") val editedDate: String)
