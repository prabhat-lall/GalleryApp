package com.example.galleryapp.factory

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryapp.ui.GalleryViewModel

class GalleryViewModelFactory(private val contentResolver: ContentResolver) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            return GalleryViewModel(contentResolver) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}