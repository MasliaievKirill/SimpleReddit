package com.masliaiev.simplereddit.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostDto(

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("subreddit")
    @Expose
    val subreddit: String,

    @SerializedName("created_utc")
    @Expose
    val dateUtc: Long,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String,

    @SerializedName("num_comments")
    @Expose
    val numberOfComments: Int,

    @SerializedName("score")
    @Expose
    val currentRaring: Int,

    @SerializedName("permalink")
    @Expose
    val postUrl: String

)
