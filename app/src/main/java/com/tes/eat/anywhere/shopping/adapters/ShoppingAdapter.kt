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
import kotlinx.android.synthetic.main.item_image.view.ivShoppingImage
import kotlinx.android.synthetic.main.item_shopping.view.*
import javax.inject.Inject

class ShoppingAdapter @Inject constructor(
    private val glide: RequestManager
):RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>(){

    class ShoppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingAdapter.ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping,
                parent,
                false
            )
        )

    }

//    private var onItemClickListener:((String)->Unit)?=null
//
//    fun setOnItemClickListener(listener:(String)->Unit){
//        onItemClickListener=listener
//    }

    override fun onBindViewHolder(holder: ShoppingAdapter.ShoppingViewHolder, position: Int) {

        //val url=images[position]
        val shoppingItem = shoppingItems[position]
        holder.itemView.apply {
            glide.load(shoppingItem.imageUrl).into(ivShoppingImage)

            tvName.text=shoppingItem.name
            val amountText = "${shoppingItem.amount}x"
            tvShoppingItemAmount.text=amountText
            val priceText ="${shoppingItem.price}£"
            tvShoppingItemPrice.text =priceText


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