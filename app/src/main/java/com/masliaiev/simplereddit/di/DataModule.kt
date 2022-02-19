package com.masliaiev.simplereddit.di

import android.app.Application
import com.masliaiev.simplereddit.data.database.AppDatabase
import com.masliaiev.simplereddit.data.database.PostDao
import com.masliaiev.simplereddit.data.network.ApiFactory
import com.masliaiev.simplereddit.data.network.ApiService
import com.masliaiev.simplereddit.data.repository.PostRepositoryImpl
import com.masliaiev.simplereddit.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindPostRepository(impl: PostRepositoryImpl): PostRepository

    companion object {

        @Provides
        @ApplicationScope
        fun providePostDao(application: Application): PostDao {
            return AppDatabase.getInstance(application).postDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}