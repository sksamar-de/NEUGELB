package com.example.movies_domain.di

import com.example.movies_domain.repository.MoviesRepository
import com.example.movies_domain.use_case.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MoviesDomainModule {

    @Provides
    @Singleton
    fun getUseCase(repository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }
}