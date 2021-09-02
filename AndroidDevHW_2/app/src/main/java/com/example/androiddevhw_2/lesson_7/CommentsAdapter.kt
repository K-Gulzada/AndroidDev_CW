package com.example.androiddevhw_2.lesson_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevhw_2.databinding.ActivityUserRecyclerBinding
import com.example.androiddevhw_2.databinding.RecyclerItemCommentBinding
import com.example.androiddevhw_2.lesson_7.model.Comment
import com.example.androiddevhw_2.lesson_7.model.User

// 02.09.21

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
    private var data: ArrayList<Comment> = arrayListOf()

    fun setData(list: ArrayList<Comment>) {
        this.data = list
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(private val binding: RecyclerItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setUser(comment: Comment) {
            binding.name.setText(comment.name)
            binding.email.setText(comment.email)
            binding.body.setText(comment.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemCommentBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val user = data[position]
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}