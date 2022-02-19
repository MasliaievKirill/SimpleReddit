package com.masliaiev.simplereddit.domain.entity

data class Post(
    val title: String,
    val author: String,
    val subreddit: String,
    val dateUtc: String,
    val thumbnail: String,
    val numberOfComments: String,
    val currentRaring: String,
    val postUrl: String
)
