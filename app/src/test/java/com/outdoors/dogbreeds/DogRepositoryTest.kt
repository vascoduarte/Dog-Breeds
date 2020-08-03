package com.outdoors.dogbreeds

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.outdoors.dogbreeds.di.DogBreedRepositoryTest
import com.outdoors.dogbreeds.network.DogBreedService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class DogRepositoryTest {

    @get:Rule
    var instantExecutorrule = InstantTaskExecutorRule()

    @Mock
    lateinit var networkService: DogBreedService

    @InjectMocks
    lateinit var dogBreedRepository: DogBreedRepositoryTest


    @Before
    fun setup()
    {
        MockitoAnnotations.initMocks(this)
        //dogBreedRepository = DogBreedRepositoryTest(networkService)
    }
    @Test
    fun `test Repository all breeds` () = runBlockingTest {

        val breedsList = dogBreedRepository.getNetworkDogBreeds()
        assertEquals("affenpinscher",breedsList[0].name)

    }
    @Test
    fun `test Repository select breed` () = runBlockingTest {

        val imageList = dogBreedRepository.getBreedImages("")
        assertEquals(3 ,imageList.size)

    }
    @Test
    fun `test Repository select subBreed` () = runBlockingTest {
        val imageList = dogBreedRepository.getSubBreedImages("","")
        assertEquals(2 ,imageList.size)
    }
}