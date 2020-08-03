package com.outdoors.dogbreeds.di

import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.domain.ImageDetails
import com.outdoors.dogbreeds.network.DogBreedService
import com.outdoors.dogbreeds.network.NetworkDogBreedContainer
import com.outdoors.dogbreeds.network.NetworkDogImagesContainer

import com.outdoors.dogbreeds.network.asDomainModel
import com.outdoors.dogbreeds.repository.DogBreedRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject


class DogBreedRepositoryTest @Inject constructor(network: DogBreedService) : DogBreedRepository(network) {

    override suspend fun getNetworkDogBreeds():List<Breed>
    {
        val str = """{"message": { "affenpinscher": [],"african": [] }}"""

        val dogList:NetworkDogBreedContainer = Json.parse(NetworkDogBreedContainer.serializer(),str)
        return dogList.asDomainModel()
    }

   override suspend fun getBreedImages(breed:String) :List<ImageDetails>
    {
        val str = """{"message": [
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
       "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg"]}"""

        val breedImages: NetworkDogImagesContainer = Json.parse(NetworkDogImagesContainer.serializer(),str)
        return breedImages.asDomainModel()
    }
  override suspend fun getSubBreedImages(breed:String, subBreed:String) :List<ImageDetails>
    {
        val str = """{"message": [
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"]}"""

        val breedImages: NetworkDogImagesContainer = Json.parse(NetworkDogImagesContainer.serializer(),str)
        return breedImages.asDomainModel()
    }
}