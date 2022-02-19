package com.masliaiev.simplereddit.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.masliaiev.simplereddit.domain.entity.Post
import com.masliaiev.simplereddit.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun getPosts(): LiveData<PagingData<Post>> {
        return postRepository.getPosts()
    }
}