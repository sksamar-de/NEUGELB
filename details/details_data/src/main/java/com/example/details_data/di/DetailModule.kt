package com.example.details_data.di

import com.example.details_data.network.DetailApi
import com.example.details_data.repository.DetailRepositoryImpl
import com.example.details_domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DetailModule {

    @Provides
    @Singleton
    fun getDetailApi(retrofit: Retrofit): DetailApi {
       return retrofit.create(DetailApi::class.java)
    }


    @Provides
    @Singleton
    fun getDetailRepository(api: DetailApi): DetailRepository {
        return DetailRepositoryImpl(api = api)
    }


}