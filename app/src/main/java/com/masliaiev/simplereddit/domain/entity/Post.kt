package com.masliaiev.simplereddit.domain.entity

data class Post(
    val title: String,
    val author: String,
    val subreddit: String,
    val dateUtc: Double,
    val thumbnail: String,
    val numberOfComments: Int,
    val currentRaring: Int
)
