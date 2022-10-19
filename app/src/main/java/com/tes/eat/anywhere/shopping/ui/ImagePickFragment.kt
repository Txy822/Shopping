package com.tes.eat.anywhere.shopping.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tes.eat.anywhere.shopping.R
import com.tes.eat.anywhere.shopping.adapters.ImageAdapter
import com.tes.eat.anywhere.shopping.other.Constants.GRID_SPAN_COUNT
import kotlinx.android.synthetic.main.fragment_image_pick.*
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    private val imageAdapter: ImageAdapter
): Fragment(R.layout.fragment_image_pick) {
    lateinit var viewModel:ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setupRecyclerview()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerview(){
        rvImages.apply {
            adapter =imageAdapter
            layoutManager=GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

}