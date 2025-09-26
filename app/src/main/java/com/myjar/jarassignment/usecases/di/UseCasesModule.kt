package com.myjar.jarassignment.usecases.di

import com.myjar.jarassignment.impl.JarRepository
import com.myjar.jarassignment.usecases.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun providesGetProductUsecase(repo:JarRepository):GetProductsUseCase{
        return GetProductsUseCase(repo)
    }

    @Provides
    @Singleton
    fun providesSearchProductUsecase(repo:JarRepository):GetProductsUseCase{
        return GetProductsUseCase(repo)
    }
}