package com.example.exercise202

import com.example.exercise202.repository.PostRepository
import com.example.exercise202.repository.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class TestRepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(): PostRepository {
        return DummyRepository()
    }
}