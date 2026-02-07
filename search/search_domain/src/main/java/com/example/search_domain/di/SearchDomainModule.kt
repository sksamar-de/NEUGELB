package com.example.search_domain.di

import com.example.search_domain.repository.SearchRepository
import com.example.search_domain.use_case.GetSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SearchDomainModule {

    @Provides
    @Singleton
    fun getSearchUseCase(repository: SearchRepository): GetSearchUseCase {
        return GetSearchUseCase(repository = repository)
    }
}