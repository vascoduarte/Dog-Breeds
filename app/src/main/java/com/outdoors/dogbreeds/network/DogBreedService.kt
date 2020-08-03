package com.outdoors.dogbreeds.network

import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {
    @GET("breeds/list/all")
    suspend fun getDogBreedList(): NetworkDogBreedContainer

    @GET("breed/{breed}/images")
    suspend fun getBreedImageList(@Path("breed") breed:String): NetworkDogImagesContainer

    @GET("breed/{breed}/{subBreed}/images")
    suspend fun getSubBreedImageList(@Path("breed") breed:String,@Path("subBreed") subBreed:String): NetworkDogImagesContainer

}

