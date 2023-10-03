package com.the_tj.weather.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.databinding.ItemsBinding
import javax.inject.Inject

class ItemsAdapter @Inject constructor():RecyclerView.Adapter<ItemsAdapter.ViewHolder>(){
    private lateinit var binding: ItemsBinding
    private lateinit var context: Context
    private var ItemsList = emptyList<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount()=differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ItemModel) {
            binding.apply {
                weatherTv.text="temprature: "+item.temp+"\ndescription: "+item.description
                locationTV.text="latitude: "+item.latitude+"\nlongitude: "+item.longitude
            }
        }
    }
    private val differCallback = object : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}