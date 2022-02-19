package com.masliaiev.simplereddit.data.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): PagingSource<Int, PostDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(postList: List<PostDbModel>)

    @Query("DELETE FROM posts")
    fun deleteAllPosts()

}