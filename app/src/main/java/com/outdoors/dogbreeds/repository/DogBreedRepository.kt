package com.outdoors.dogbreeds.repository

import android.util.Log
import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.domain.ImageDetails
import com.outdoors.dogbreeds.network.DogBreedService
import com.outdoors.dogbreeds.network.asDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DogBreedRepository @Inject constructor(private val network:DogBreedService){


    open suspend fun getNetworkDogBreeds():List<Breed>
    {
        Log.i("REPO","getNetworkDogBreeds()")
        val dogList = network.getDogBreedList()
        return dogList.asDomainModel()
    }

    open suspend fun getBreedImages(breed:String) :List<ImageDetails>
    {
        val breedImages = network.getBreedImageList(breed)
        return breedImages.asDomainModel()
    }
    open suspend fun getSubBreedImages(breed:String, subBreed:String) :List<ImageDetails>
    {
        val breedImages = network.getSubBreedImageList(breed,subBreed)
         return breedImages.asDomainModel()
    }
}