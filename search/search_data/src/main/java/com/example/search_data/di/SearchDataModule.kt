package com.example.search_data.di

import com.example.search_data.network.SearchAPI
import com.example.search_data.repository.SearchRepositoryImpl
import com.example.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Provides
    @Singleton
    fun getSearchAPI(retrofit: Retrofit): SearchAPI {
       return retrofit.create(SearchAPI::class.java)
    }

    @Provides
    @Singleton
    fun getSearchRepository(api: SearchAPI): SearchRepository {
        return SearchRepositoryImpl(api = api)
    }
}