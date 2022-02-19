package com.masliaiev.simplereddit.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.masliaiev.simplereddit.domain.entity.Post
import io.reactivex.Single

interface PostRepository {

    fun loadPosts(): Single<List<Post>>

    fun getPosts(): LiveData<PagingData<Post>>

    fun insertPosts(postList: List<Post>)

}