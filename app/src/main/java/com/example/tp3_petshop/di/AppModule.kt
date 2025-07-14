package com.example.tp3_petshop.di

import com.example.tp3_petshop.repository.ProductRepository
import com.example.tp3_petshop.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = ProductRepository()

    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository = CartRepository()
}