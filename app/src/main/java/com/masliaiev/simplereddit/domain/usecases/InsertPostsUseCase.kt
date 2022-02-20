package com.masliaiev.simplereddit.domain.usecases

import com.masliaiev.simplereddit.domain.entity.Post
import com.masliaiev.simplereddit.domain.repository.PostRepository
import javax.inject.Inject

class InsertPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    fun insertPosts(postList: List<Post>) {
        return postRepository.insertPosts(postList)
    }

}