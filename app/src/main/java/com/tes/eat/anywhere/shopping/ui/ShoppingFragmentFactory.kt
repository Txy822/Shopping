package com.tes.eat.anywhere.shopping.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.tes.eat.anywhere.shopping.adapters.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class ShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager
) :FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

       return when(className){
           ImagePickFragment::class.java.name ->ImagePickFragment(imageAdapter)
          AddShoppingItemFragment::class.java.name ->AddShoppingItemFragment(glide)
           else -> super.instantiate(classLoader, className)
       }
    }
}