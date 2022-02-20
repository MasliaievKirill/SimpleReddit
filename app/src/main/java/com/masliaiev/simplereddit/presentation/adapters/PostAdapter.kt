package com.masliaiev.simplereddit.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.masliaiev.simplereddit.R
import com.masliaiev.simplereddit.databinding.PosterItemBinding
import com.masliaiev.simplereddit.domain.entity.Post
import com.squareup.picasso.Picasso

class PostAdapter: PagingDataAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    var onPostClickListener: OnPostClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PosterItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {
            Picasso.get().load(post.thumbnail).placeholder(R.drawable.ic_image_placeholder)
                .into(holder.binding.ivThumbnail)
            with(holder.binding){
                tvAuthor.text = post.author
                tvTitle.text = post.title
                tvSubreddit.text = post.subreddit
                tvNumberOfComments.text = post.numberOfComments
                tvRating.text = post.currentRaring
                tvDate.text = post.dateUtc
                root.setOnClickListener {
                    onPostClickListener?.onPosterClick(post.postUrl)
                }
            }
        }
    }

    interface OnPostClickListener {
        fun onPosterClick(url: String)
    }
}