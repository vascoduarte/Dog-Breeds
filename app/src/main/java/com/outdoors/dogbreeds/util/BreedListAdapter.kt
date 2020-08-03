package com.outdoors.dogbreeds.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.outdoors.dogbreeds.databinding.BreedListItemBinding
import com.outdoors.dogbreeds.domain.Breed

class BreedListAdapter(val clickListener: BreedListClickListener): ListAdapter<Breed, BreedListAdapter.ViewHolder>(BreedListDiffCallBack())
{
    override fun onBindViewHolder(holder: BreedListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding:BreedListItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(clickListener: BreedListClickListener, item:Breed)
        {
            binding.breedItem=item
            binding.breedItemClick = clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup):ViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BreedListItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}
class BreedListDiffCallBack():DiffUtil.ItemCallback<Breed>()
{
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.name == newItem.name
    }
    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.name == newItem.name
    }
}

class BreedListClickListener(val clickListener: (breed: Breed) -> Unit)
{
    fun onClick(item:Breed) = clickListener(item)
}