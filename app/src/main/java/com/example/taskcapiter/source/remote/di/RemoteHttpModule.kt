package com.example.taskcapiter.source.remote.di

import com.example.taskcapiter.source.remote.interceptors.ConnectionInterceptor
import com.example.taskcapiter.source.remote.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteHttpModule {

    private const val TIME_OUT = 60L

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: LoggingInterceptor,
        connectionInterceptor: ConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectionInterceptor)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }


}