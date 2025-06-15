package com.tops.showdatainrecyclerview_withapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.showdatainrecyclerview_withapi.databinding.ItemRow1Binding
import com.tops.showdatainrecyclerview_withapi.model.User

class MyAdapter2(private val items: ArrayList<User>): RecyclerView.Adapter<MyAdapter2.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
       val binding: ItemRow1Binding = ItemRow1Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = items[position]
        holder.binding.tvID.setText("UserID = ${user.id}")
        holder.binding.tvName.setText("Name = ${user.name}")
        holder.binding.tvEmail.setText("Email = ${user.email}")
        holder.binding.tvBody.setText("Email = ${user.body}")

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class UserViewHolder(val binding: ItemRow1Binding): RecyclerView.ViewHolder(binding.root)

}