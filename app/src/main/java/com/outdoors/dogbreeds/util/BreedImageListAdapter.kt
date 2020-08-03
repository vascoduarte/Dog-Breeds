package com.outdoors.dogbreeds.util

import com.outdoors.dogbreeds.databinding.BreedImageItemBinding
import com.outdoors.dogbreeds.domain.ImageDetails



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ImageListAdapter(val clickListener: ImageListClickListener ): ListAdapter<ImageDetails, ImageListAdapter.ViewHolder>(ImageListDiffCallBack())
{
    override fun onBindViewHolder(holder: ImageListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding:BreedImageItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(clickListener: ImageListClickListener, item:ImageDetails)
        {
            binding.breedDetail=item
            binding.imageClick=clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup):ViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BreedImageItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}
class ImageListDiffCallBack():DiffUtil.ItemCallback<ImageDetails>()
{
    override fun areItemsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
        return oldItem.url == newItem.url
    }
    override fun areContentsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
        return oldItem.url == newItem.url
    }
}
class ImageListClickListener(val clickListener: (image: ImageDetails) -> Unit)
{
    fun onClick(item: ImageDetails) = clickListener(item)
}

