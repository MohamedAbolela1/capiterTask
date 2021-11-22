package com.example.taskcapiter.source.remote.di

import com.example.taskcapiter.main.data.apiInterface.RetrofitApisInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                )
            ).client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): RetrofitApisInterface {
        return retrofit.create(RetrofitApisInterface::class.java)
    }
}
