package com.example.galleryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.galleryapp.R
import com.example.galleryapp.model.Image

class ImageAdapter(
    private val context: Context,
    private val list: List<Image>,
    private val imageIconClicked: (Image) -> Unit
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = list[position]
        Glide.with(context).load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder!!.image)
        holder.image.setOnClickListener {
            imageIconClicked(currentImage)
        }


    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.row_image)

    }

}
