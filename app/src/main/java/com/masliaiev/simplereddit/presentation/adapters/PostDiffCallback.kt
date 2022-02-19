package com.masliaiev.simplereddit.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.masliaiev.simplereddit.domain.entity.Post

class PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}