package com.example.taskcapiter.source.remote.di

import com.example.taskcapiter.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object NetworkUrlModule {
    @Provides
    @Singleton
    @BaseUrl
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}