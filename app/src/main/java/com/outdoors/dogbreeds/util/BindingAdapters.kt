package com.outdoors.dogbreeds.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.outdoors.dogbreeds.R
import com.outdoors.dogbreeds.domain.Breed
import com.outdoors.dogbreeds.domain.ImageDetails
import com.outdoors.dogbreeds.network.NetworkResource
import com.outdoors.dogbreeds.network.NetworkStatus


@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it:Any?)
{
    it?.let{
        view.visibility =
            when (it)
            {
                NetworkStatus.SUCCESS -> View.GONE
                NetworkStatus.ERROR -> View.GONE
                else -> View.VISIBLE
            }
    }

}

@BindingAdapter("showPrettyName")
fun showPrettyName(view: TextView, it: String)
{
    view.text =it.showPrettyName()
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let{
        val imgUri=imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.ic_breed_placeholder)
            .error(R.drawable.ic_breed_error)
            .centerCrop()
            .into(imgView)
    }
}
@BindingAdapter("showFullImage")
fun showFullImage(view: ImageView, it:ImageDetails?)
{

    it?.let {
        val imgUri=it.url.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .placeholder(R.drawable.ic_breed_placeholder)
            .error(R.drawable.ic_breed_error)
            .into(view)
    }
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}
@BindingAdapter("errorText")
fun errorText(view: TextView, it:String?)
{

    if(it==null)
    {
        view.visibility=View.GONE
    }
    else
    {
        view.visibility=View.VISIBLE
        view.text = it
    }

}
@BindingAdapter("errorCardVisibility")
fun errorCardVisibility(view: CardView, it:Any?)
{
    it?.let{
        view.visibility =
            when (it)
            {
                NetworkStatus.SUCCESS -> View.GONE
                NetworkStatus.LOADING -> View.GONE
                else -> View.VISIBLE
            }
    }

}