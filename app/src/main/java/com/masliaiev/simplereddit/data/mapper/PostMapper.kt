package com.masliaiev.simplereddit.data.mapper

import com.masliaiev.simplereddit.data.database.PostDbModel
import com.masliaiev.simplereddit.data.network.model.PostDto
import com.masliaiev.simplereddit.domain.entity.Post
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun mapPostEntityToPostDbModel(post: Post): PostDbModel {
        return PostDbModel(
            id = UNDEFINED_ID,
            title = post.title,
            author = post.author,
            subreddit = post.subreddit,
            dateUtc = post.dateUtc,
            thumbnail = post.thumbnail,
            numberOfComments = post.numberOfComments,
            currentRaring = post.currentRaring,
            postUrl = post.postUrl
        )
    }

    fun mapPostDbModelToPostEntity(postDbModel: PostDbModel): Post {
        return Post(
            title = postDbModel.title,
            author = postDbModel.author,
            subreddit = postDbModel.subreddit,
            dateUtc = postDbModel.dateUtc,
            thumbnail = postDbModel.thumbnail,
            numberOfComments = postDbModel.numberOfComments,
            currentRaring = postDbModel.currentRaring,
            postUrl = postDbModel.postUrl
        )
    }

    fun mapPostDtoToPostEntity(postDto: PostDto): Post {
        return Post(
            title = postDto.title,
            author = postDto.author,
            subreddit = postDto.subreddit,
            dateUtc = convertTimestampToTimeAgo(postDto.dateUtc) ,
            thumbnail = postDto.thumbnail,
            numberOfComments = postDto.numberOfComments.toString(),
            currentRaring = postDto.currentRaring.toString(),
            postUrl = BASE_URL + postDto.postUrl
        )
    }

    private fun convertTimestampToTimeAgo(timestamp: Long): String {
        val currentTimeInSeconds = System.currentTimeMillis() / MILLIS_IN_SECOND
        val difference = currentTimeInSeconds - timestamp
        val hours = difference / SECONDS_IN_HOUR
        return when {
            hours.toInt() < ONE_HOUR -> {
                "Less than an hour ago"
            }
            hours.toInt() == ONE_HOUR -> {
                "${hours.toInt()} hour ago"
            }
            else -> {
                "${hours.toInt()} hours ago"
            }
        }
    }

    companion object {
        private const val UNDEFINED_ID = 0
        private const val MILLIS_IN_SECOND = 1000L
        private const val SECONDS_IN_HOUR = 3600
        private const val ONE_HOUR = 1
        private const val BASE_URL = "https://www.reddit.com"
    }

}