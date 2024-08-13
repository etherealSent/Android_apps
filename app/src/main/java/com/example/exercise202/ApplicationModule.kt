package com.example.exercise202

import dagger.Module
import dagger.Provides
import kotlin.random.Random

@Module
class ApplicationModule {
    @Provides
    fun provideRandom(): Random = Random

    @Provides
    fun provideNumberRepository(random: Random): NumberRepository =
        NumberRepositoryImpl(random)
}