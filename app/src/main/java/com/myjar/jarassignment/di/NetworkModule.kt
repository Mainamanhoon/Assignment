package com.myjar.jarassignment.di

import com.myjar.jarassignment.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.restful-api.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }


}