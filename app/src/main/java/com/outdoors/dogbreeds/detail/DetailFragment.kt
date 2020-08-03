package com.outdoors.dogbreeds.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.outdoors.dogbreeds.R
import com.outdoors.dogbreeds.databinding.DetailFragmentBinding
import com.outdoors.dogbreeds.network.NetworkStatus
import com.outdoors.dogbreeds.util.ImageListAdapter
import com.outdoors.dogbreeds.util.ImageListClickListener
import com.outdoors.dogbreeds.util.showPrettyName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private val  detailViewModel:DetailViewModel by viewModels()

    private lateinit var onBackPress:OnBackPressedCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(inflater,R.layout.detail_fragment,container,false)

        val arguments = DetailFragmentArgs.fromBundle(requireArguments())

        requireActivity().breedsToolbar.title=arguments.breed.name.showPrettyName()

        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val breedImagesManager = GridLayoutManager(activity,2)
        binding.breedImageView.layoutManager = breedImagesManager
        val imageListAdapter = ImageListAdapter(ImageListClickListener { image ->
          detailViewModel.onOpenFullImage(image)
            })
        binding.breedImageView.adapter = imageListAdapter

        detailViewModel.imageList.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it.status== NetworkStatus.SUCCESS)
                {
                    imageListAdapter.submitList(it.data)
                }

            }
        })
        onBackPress  = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner)
        {
            detailViewModel.onCloseFullImage()
        }

        detailViewModel.selectedImage.observe(viewLifecycleOwner, Observer {
            onBackPress.isEnabled = detailViewModel.selectedImage.value!=null
        })

        return binding.root
    }

}