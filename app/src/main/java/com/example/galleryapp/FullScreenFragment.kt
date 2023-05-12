package com.example.galleryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.FragmentFullScreenBinding
import com.example.galleryapp.utils.Constants.IMAGE_NAME
import com.example.galleryapp.utils.Constants.IMAGE_PATH


class FullScreenFragment : Fragment() {

    lateinit var binding: FragmentFullScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePath = arguments?.getString(IMAGE_PATH)
        val imageName = arguments?.getString(IMAGE_NAME)

        Glide.with(this).load(imagePath)
            .into(binding.fullImage)

    }

}