package com.tes.eat.anywhere.shopping.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.tes.eat.anywhere.shopping.adapters.ImageAdapter
import com.tes.eat.anywhere.shopping.adapters.ShoppingAdapter
import com.tes.eat.anywhere.shopping.repository.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingAdapter: ShoppingAdapter
) :FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

       return when(className){
           ImagePickFragment::class.java.name ->ImagePickFragment(imageAdapter)
          AddShoppingItemFragment::class.java.name ->AddShoppingItemFragment(glide)
          ShoppingFragment::class.java.name -> ShoppingFragment(shoppingAdapter,
              ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
          )
           else -> super.instantiate(classLoader, className)
       }
    }
}