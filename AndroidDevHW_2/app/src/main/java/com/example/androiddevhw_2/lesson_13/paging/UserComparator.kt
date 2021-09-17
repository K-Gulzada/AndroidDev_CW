package com.example.androiddevhw_2.lesson_13.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.androiddevhw_2.lesson_12.room.User

object UserComparator:DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        // добить
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        // добить
    }
}