package com.example.movies_data.di

import com.example.movies_data.network.MoviesApi
import com.example.movies_data.repository.MoviesRepositoryImpl
import com.example.movies_domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MoviesDataModule {

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

}