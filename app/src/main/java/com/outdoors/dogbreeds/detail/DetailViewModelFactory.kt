package com.outdoors.dogbreeds.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.outdoors.dogbreeds.domain.Breed
import javax.inject.Inject
//At this time Hilt doesn't support safe args params
/*class DetailViewModelFactory (private val breed: Breed) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(breed) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/