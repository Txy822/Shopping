package com.tes.eat.anywhere.shopping.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.tes.eat.anywhere.shopping.R
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import kotlinx.android.synthetic.main.item_image.view.*
import javax.inject.Inject

class ShoppingAdapter @Inject constructor(
    private val glide: RequestManager
):RecyclerView.Adapter<ShoppingAdapter.ImageViewHolder>(){
    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object:DiffUtil.ItemCallback<ShoppingItem>(){
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.hashCode()==newItem.hashCode()
        }

    }
    private val differ=AsyncListDiffer(this,diffCallback)

    var shoppingItems: List<ShoppingItem>
    get() =differ.currentList
    set(value)=differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        )

    }

    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){
        onItemClickListener=listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        //val url=images[position]
        val shoppingItem = shoppingItems[position]
        holder.itemView.apply {
            glide.load(shoppingItem).into(ivShoppingImage)

//            setOnClickListener{
//                onItemClickListener?.let { click ->
//                    click(shoppingItem)
//                }
//            }
        }
    }

    override fun getItemCount(): Int {
return shoppingItems.size
    }
}