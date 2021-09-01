package com.example.androiddevhw_2.lesson_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevhw_2.databinding.ActivityUserRecyclerBinding
import com.example.androiddevhw_2.lesson_7.model.User

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.UserViewHolder>() {
    private var data: ArrayList<User> = arrayListOf()

    fun setData(list: ArrayList<User>) {
        this.data = list
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ActivityUserRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setUser(user: User) {
            binding.name.setText(user.name)
            binding.email.setText(user.email)
            binding.gender.setText(user.gender.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityUserRecyclerBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}