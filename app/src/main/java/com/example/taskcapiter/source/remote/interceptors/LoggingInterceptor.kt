package com.example.taskcapiter.source.remote.interceptors

import com.example.taskcapiter.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                this.level = HttpLoggingInterceptor.Level.BODY
            else
                this.level = HttpLoggingInterceptor.Level.NONE
        }
        return chain.proceed(chain.request())
    }
}
