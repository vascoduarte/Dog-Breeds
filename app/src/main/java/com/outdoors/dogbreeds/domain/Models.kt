package com.outdoors.dogbreeds.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject


@Parcelize
data class Breed(
    var id:String,
    var name:String,
    var mainBreed:String,
    var subBreed:String=""
):Parcelable

data class ImageDetails(
    var url:String)