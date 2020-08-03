package com.outdoors.dogbreeds.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.network.NetworkResource
import com.outdoors.dogbreeds.repository.DogBreedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val dogRepository:DogBreedRepository): ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _breedList= MutableLiveData<NetworkResource<List<Breed>>>()
    val breedList:LiveData<NetworkResource<List<Breed>>>
        get() = _breedList

    private var _navigateToDetails: MutableLiveData<Breed> = MutableLiveData()
    val navigateToDetails:LiveData<Breed>
        get() = _navigateToDetails

    init {
        _navigateToDetails.value = null
        fetchBreedList()
    }

    fun fetchBreedList() {
        viewModelScope.launch {
            _breedList.value = NetworkResource.loading(data = null)
            try {
                _breedList.value = NetworkResource.success(data = dogRepository.getNetworkDogBreeds())
            }
            catch (exception: Exception) {
                _breedList.value =NetworkResource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"

                )
            }
        }
    }
    fun onBreedItemClicked(breed:Breed)
    {
        _navigateToDetails.value=breed
    }
    fun onNavigationToDetailsComplete()
    {
        _navigateToDetails.value=null
    }
}