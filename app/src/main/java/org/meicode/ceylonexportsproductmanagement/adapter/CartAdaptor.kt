package org.meicode.ceylonexportsproductmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import org.meicode.ceylonexportsproductmanagement.databinding.LayoutCartItemBinding
import org.meicode.ceylonexportsproductmanagement.roomdb.ProductModel

class CartAdaptor (val context: Context, val list: ArrayList<ProductModel>):
RecyclerView.Adapter<CartAdaptor.CartViewHolder>(){

    inner class CartViewHolder( val binding : LayoutCartItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = LayoutCartItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Glide.with(context).load(list[position].productImage).into(holder.binding.imageView4)
        holder.binding.textView11.text = list[position].productName
        holder.binding.textView12.text = list[position].productSp

    }

}





