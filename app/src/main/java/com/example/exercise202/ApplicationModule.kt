package com.example.exercise202

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideRandom(): Random = Random

    @Provides
    fun provideNumberRepository(random: Random): NumberRepository =
        NumberRepositoryImpl(random)
}