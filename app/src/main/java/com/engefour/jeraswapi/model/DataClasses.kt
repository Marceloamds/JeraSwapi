package com.engefour.jeraswapi.model

import java.util.*

//Data Classes para serem usadas na aplicação em si

data class  Filme( val title:String,
                  val episodeId:Int,
                  val openingCrawl:String,
                  val director:String,
                  val producer:String,
                  val releaseDate:Date,
                  val species: ArrayList<String>,
                  val starships: ArrayList<String>,
                  val vehicles: ArrayList<String>,
                  val characters: ArrayList<String>,
                  val planets: ArrayList<String>,
                  val url: String,
                  val creationDate: String,
                  val editedDate: String)

data class  Especie( val name:String,
                    val classification:String,
                    val designation:String,
                    val averageHeight:String,
                    val averageLifespan:String,
                    val eyeColors: String,
                    val hairColors: String,
                    val skinColors: String,
                    val language: String,
                    val homeworld: String,
                    val peopleUrls: ArrayList<String>,
                    val filmsUrls: ArrayList<String>,
                    val url: String,
                    val creationDate: String,
                    val editedDate: String)

data class  Nave( val name:String,
                      val model: String,
                      val manufacturer: String,
                      val costInCredits: String,
                      val length:String,
                      val crew: String,
                      val passengers: String,
                      val maxAtmospheringSpeed: String,
                      val hyperdriveRating: String,
                      val mglt: String,
                      val cargoCapacity: String,
                      val consumables: String,
                      val filmsUrls: ArrayList<String>,
                      val pilotsUrls: ArrayList<String>,
                      val url: String,
                      val creationDate: String,
                      val editedDate: String)

data class  Veiculo( val name:String,
                     val model:String,
                     val vehicleClass:String,
                     val manufacturer:String,
                     val length:String,
                     val costInCredits:String,
                     val crew: String,
                     val passengers: String,
                     val maxAtmospheringSpeed: String,
                     val cargoCapacity: String,
                     val consumables: String,
                     val filmsUrls: ArrayList<String>?,
                     val pilotsUrls: ArrayList<String>?,
                     val url: String,
                     val creationDate: String,
                     val editedDate: String)

data class  Pessoa( val name:String,
                    val birthYear:String,
                    val eyeColor:String,
                    val gender:String,
                    val hairColor: String,
                    val height: String,
                    val mass: String,
                    val skinColor: String,
                    val homeworld: String,
                    val filmsUrls: ArrayList<String>,
                    val speciesUrls: ArrayList<String>,
                    val starshipsUrls: ArrayList<String>,
                    val vehiclesUrls: ArrayList<String>,
                    val url: String,
                    val creationDate: String,
                    val editedDate: String)

data class  Planeta( val name:String,
                    val diameter:String,
                    val rotationPeriod:String,
                    val orbitalPeriod:String,
                    val gravity:String,
                    val population: String,
                    val climate: String,
                    val terrain: String,
                    val surfaceWater: String,
                    val residents: ArrayList<String>?,
                    val films: ArrayList<String>?,
                    val url: String,
                    val creationDate: String,
                    val editedDate: String)
