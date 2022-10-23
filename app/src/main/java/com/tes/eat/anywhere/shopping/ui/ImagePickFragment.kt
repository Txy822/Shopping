package com.tes.eat.anywhere.shopping.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tes.eat.anywhere.shopping.R
import com.tes.eat.anywhere.shopping.adapters.ImageAdapter
import com.tes.eat.anywhere.shopping.other.Constants.GRID_SPAN_COUNT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_pick.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tes.eat.anywhere.shopping.other.Constants.SEARCH_TIME_DELAY
import com.tes.eat.anywhere.shopping.other.Status

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shopping.*

import kotlinx.coroutines.delay


@AndroidEntryPoint
class ImagePickFragment @Inject constructor(
    val imageAdapter: ImageAdapter
): Fragment(R.layout.fragment_image_pick) {
    lateinit var viewModel:ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setupRecyclerview()
        subscribeToObservers()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchForImage(editable.toString())
                    }
                }
            }
        }

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun subscribeToObservers() {
        viewModel.images.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { result ->
                when(result.status) {
                    Status.SUCCESS -> {
                        val urls = result.data?.imageResponse?.map { imageResult ->  imageResult.previewURL }
                        imageAdapter.images = urls ?: listOf()
                        System.out.println("url was not empty: $urls")
                        progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        val view = requireActivity().findViewById<View>(android.R.id.content)

                        Snackbar.make(
                            view,
                            result.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }


    private fun setupRecyclerview(){
        rvImages.apply {
            adapter =imageAdapter
            layoutManager=GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

}