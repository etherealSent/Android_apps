package com.example.exercise202.repository

import com.example.exercise202.api.JsonPHService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(jsonPHService: JsonPHService): PostRepository {
        return PostRepositoryImpl(jsonPHService)
    }
}