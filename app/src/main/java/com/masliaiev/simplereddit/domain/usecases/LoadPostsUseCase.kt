package com.masliaiev.simplereddit.domain.usecases

import com.masliaiev.simplereddit.domain.entity.Post
import com.masliaiev.simplereddit.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    fun loadPosts(): Single<List<Post>> {
       return postRepository.loadPosts()
    }

}