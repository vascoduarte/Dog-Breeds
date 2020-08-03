package com.outdoors.dogbreeds.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.outdoors.dogbreeds.R
import com.outdoors.dogbreeds.databinding.MainFragmentBinding
import com.outdoors.dogbreeds.network.NetworkStatus
import com.outdoors.dogbreeds.util.BreedListAdapter
import com.outdoors.dogbreeds.util.BreedListClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(inflater,R.layout.main_fragment,container,false)

        binding.mainViewModel=viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val breedListManager = LinearLayoutManager(activity)
        binding.breedListView.layoutManager = breedListManager
        val breedListAdapter = BreedListAdapter(BreedListClickListener { breed ->viewModel.onBreedItemClicked(breed) })
        binding.breedListView.adapter = breedListAdapter

        viewModel.breedList.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it.status==NetworkStatus.SUCCESS)
                {
                    breedListAdapter.submitList(it.data)
                }

            }
        })
        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(it))
                viewModel.onNavigationToDetailsComplete()
            }
        })
        return binding.root
    }
}