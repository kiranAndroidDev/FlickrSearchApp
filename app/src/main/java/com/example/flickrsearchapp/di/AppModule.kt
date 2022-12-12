package com.example.flickrsearchapp.di

import com.example.flickrsearchapp.repo.PhotoRepository
import com.example.flickrsearchapp.repo.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun photoRepository(impl: PhotoRepositoryImpl): PhotoRepository = impl
}
