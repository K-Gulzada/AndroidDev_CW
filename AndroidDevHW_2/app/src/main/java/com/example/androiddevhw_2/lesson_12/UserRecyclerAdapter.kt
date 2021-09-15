package com.example.androiddevhw_2.lesson_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevhw_2.databinding.RecyclerItemUser12Binding
import com.example.androiddevhw_2.lesson_12.room.User


class UserRecyclerAdapter : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    private var data: List<User> = listOf()

    fun setData(list: List<User>) {
        data = list
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: RecyclerItemUser12Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(user: User) {
            binding.firstName.text = user.firstName
            binding.lastName.text = user.secondName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            RecyclerItemUser12Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


}