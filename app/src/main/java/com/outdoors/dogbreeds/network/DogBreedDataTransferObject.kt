package com.outdoors.dogbreeds.network

import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.domain.ImageDetails
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class NetworkDogBreedContainer(
    val message: JsonObject
)

fun NetworkDogBreedContainer.asDomainModel(): List<Breed>
{
    val convertedList = mutableListOf<Breed>()

    message.keys.forEach {
        val jsonObject = message[it]?.jsonArray

       if(jsonObject.isNullOrEmpty())
       {
           convertedList.add(Breed(
               id="$it",
               name = "$it",
               mainBreed = it
           ))
       }
       else
       {
           jsonObject.forEach {sub->
              val subName = sub.toString().replace("\"","")
               convertedList.add(Breed(
                   id="$it-$subName",
                   name = "$it $subName",
                   mainBreed = it,
                   subBreed = subName
               ))
           }
       }
    }
    return convertedList
}

@Serializable
data class NetworkDogImagesContainer(
    val message:List<String>
)

fun NetworkDogImagesContainer.asDomainModel(): List<ImageDetails>
{
    return message.map {
        ImageDetails(url = it)
    }
}
