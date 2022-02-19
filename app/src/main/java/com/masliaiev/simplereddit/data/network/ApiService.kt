package com.masliaiev.simplereddit.data.network

import com.masliaiev.simplereddit.data.network.model.ServerResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top.json")
    fun getTopPosts(
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT
    ): Single<ServerResponseDto>

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val LIMIT = 50
    }
}