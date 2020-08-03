package com.outdoors.dogbreeds.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.domain.ImageDetails
import com.outdoors.dogbreeds.network.NetworkResource
import com.outdoors.dogbreeds.repository.DogBreedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//At this time Hilt doesn't support safe args params
class DetailViewModel @ViewModelInject constructor(private val dogRepository:DogBreedRepository,
                                                   @Assisted savedStateHandle: SavedStateHandle):ViewModel() {

    private val breed:Breed? = savedStateHandle["breed"]
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    private var _imageList= MutableLiveData<NetworkResource<List<ImageDetails>>>()
    val imageList: LiveData<NetworkResource<List<ImageDetails>>>
        get() = _imageList

    private var _selectedImage: MutableLiveData<ImageDetails> = MutableLiveData()
    val selectedImage: LiveData<ImageDetails>
        get() = _selectedImage

    init {
        _selectedImage.value =null
        fetchBreedImages()
    }

   fun fetchBreedImages()
    {
        viewModelScope.launch {
            _imageList.value = NetworkResource.loading(data = null)
            try {
                if(breed?.subBreed!="") _imageList.value = NetworkResource.success(data = dogRepository.getSubBreedImages(breed!!.mainBreed,breed!!.subBreed))
                else _imageList.value = NetworkResource.success(data = dogRepository.getBreedImages(breed.mainBreed))
            }
            catch (exception: Exception) {
                _imageList.value = NetworkResource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"

                )
            }
        }

    }
    fun onOpenFullImage(img:ImageDetails)
    {
        _selectedImage.value=img
    }
    fun onCloseFullImage()
    {
        _selectedImage.value=null
    }
}