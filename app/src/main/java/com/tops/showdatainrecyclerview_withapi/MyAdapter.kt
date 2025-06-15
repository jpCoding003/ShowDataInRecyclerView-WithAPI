package com.tops.showdatainrecyclerview_withapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.showdatainrecyclerview_withapi.databinding.ActivityMainBinding.*
import com.tops.showdatainrecyclerview_withapi.databinding.ItemRowBinding

class MyAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.binding.tvName.text = items[position]

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(text: String){
        items.add(text)
        notifyItemInserted(items.size-1)
    }

}