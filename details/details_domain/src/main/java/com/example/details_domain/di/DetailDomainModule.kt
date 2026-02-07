package com.example.details_domain.di

import com.example.details_domain.repository.DetailRepository
import com.example.details_domain.use_case.GetDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DetailDomainModule {


    @Provides
    @Singleton
    fun getDetailUseCase(repository: DetailRepository): GetDetailUseCase {
        return GetDetailUseCase(repository = repository)
    }

}