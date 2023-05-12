package com.example.galleryapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.adapter.ImageAdapter
import com.example.galleryapp.databinding.FragmentMainBinding
import com.example.galleryapp.factory.GalleryViewModelFactory
import com.example.galleryapp.model.Image
import com.example.galleryapp.ui.GalleryViewModel


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel: GalleryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ContentResolver instance
        val contentResolver = requireActivity().contentResolver

        // Initialize the ViewModel with the ContentResolver
        viewModel = ViewModelProvider(this, GalleryViewModelFactory(contentResolver)).get(
            GalleryViewModel::class.java)

        // Observe the result of getAllImages() and set the adapter when the list is available
        viewModel.getAllImages().observe(viewLifecycleOwner) { allPictures ->
            if (allPictures.isNotEmpty()) {
                adapter = ImageAdapter(requireContext(), allPictures,::onImageItemClicked)
                binding.imageRecycler.adapter = adapter
                binding.recyclerProgres.visibility = View.GONE
            }
        }

        binding.imageRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.imageRecycler.setHasFixedSize(true)

        // Storage Permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        }

        binding.recyclerProgres.visibility = View.VISIBLE

    }

    private fun onImageItemClicked(currentImage : Image){
        val bundle = Bundle()
        bundle.putString("PATH", currentImage.imagePath)
        bundle.putString("NAME", currentImage.imageName)
        findNavController().navigate(R.id.action_mainFragment_to_fullScreenFragment,bundle)
    }


}