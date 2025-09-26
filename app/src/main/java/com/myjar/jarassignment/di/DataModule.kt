package com.myjar.jarassignment.di

import com.myjar.jarassignment.data.api.ApiService
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import com.myjar.jarassignment.impl.JarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesJarRepository(apiService: ApiService):JarRepository{
        return JarRepositoryImpl(apiService)
    }
}