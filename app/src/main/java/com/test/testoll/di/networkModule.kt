package com.test.testoll.di

import com.google.gson.GsonBuilder
import com.test.testoll.network.APIService
import com.test.testoll.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://oll.tv"

private const val TIMEOUT = 60L

val networkModule = module {

    single { GsonBuilder().create() }

    single {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder().apply {
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(APIService::class.java) }
}