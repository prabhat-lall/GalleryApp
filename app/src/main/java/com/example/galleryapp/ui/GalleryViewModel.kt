package com.example.galleryapp.ui

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapp.model.Image
import com.example.galleryapp.repository.ImageRepository

class GalleryViewModel(contentResolver: ContentResolver) : ViewModel() {

    private var galleryRepository : ImageRepository = ImageRepository(contentResolver)


    fun getAllImages(): LiveData<List<Image>> {
        val imagesLiveData = MutableLiveData<List<Image>>()
        val images = galleryRepository.getAllImages()
        imagesLiveData.value = images
        return imagesLiveData
    }
}