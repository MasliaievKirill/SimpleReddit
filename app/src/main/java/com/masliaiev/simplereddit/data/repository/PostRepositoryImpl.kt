package com.masliaiev.simplereddit.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.masliaiev.simplereddit.data.database.PostDao
import com.masliaiev.simplereddit.data.mapper.PostMapper
import com.masliaiev.simplereddit.data.network.ApiService
import com.masliaiev.simplereddit.data.network.model.ServerResponseDto
import com.masliaiev.simplereddit.domain.entity.Post
import com.masliaiev.simplereddit.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val apiService: ApiService,
    private val mapper: PostMapper,
) : PostRepository {

    override fun loadPosts(): Single<List<Post>> {
        return apiService.getTopPosts().map {
            getPostList(it)
        }
    }

    override fun getPosts(): LiveData<PagingData<Post>> {
        return Pager(
            PagingConfig(
                pageSize = MAX_NUMBER_OF_ITEMS_LOADED_AT_ONCE,
                enablePlaceholders = true,
                maxSize = MAX_NUMBER_OF_ITEMS
            )
        ) {
            postDao.getPosts()
        }.liveData.map {
            it.map {
                mapper.mapPostDbModelToPostEntity(it)
            }
        }
    }

    override fun insertPosts(postList: List<Post>) {
        postDao.deleteAllPosts()
        postDao.insertPosts(postList.map {
            mapper.mapPostEntityToPostDbModel(it)
        })
    }

    private fun getPostList(serverResponseDto: ServerResponseDto): List<Post> {
        val postList = ArrayList<Post>()
        for (child in serverResponseDto.dataDto.children) {
            postList.add(mapper.mapPostDtoToPostEntity(child.postDto))
        }
        return postList
    }

    companion object {
        private const val MAX_NUMBER_OF_ITEMS_LOADED_AT_ONCE = 10
        private const val MAX_NUMBER_OF_ITEMS = 100
    }

}