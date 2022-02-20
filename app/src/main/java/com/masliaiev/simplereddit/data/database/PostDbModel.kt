package com.masliaiev.simplereddit.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String,
    val subreddit: String,
    val dateUtc: String,
    val thumbnail: String,
    val numberOfComments: String,
    val currentRaring: String,
    val postUrl: String
)
