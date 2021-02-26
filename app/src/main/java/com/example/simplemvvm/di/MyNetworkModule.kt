package com.example.simplemvvm.di

import com.example.simplemvvm.feature.home.network.HomeService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class MyNetworkModule(private val url: String) {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return retrofitFactory(okHttpClient, url)
    }

    @Provides
    @Singleton
    fun provideMyApiService(retrofit: Retrofit): MyApiService {
        return retrofit.create(MyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = okHttpClientFactory()

    private fun retrofitFactory(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    private fun okHttpClientFactory(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(defaultHTTPClient())
            .addInterceptor(serviceHTTPClient())
            .addInterceptor(httpLoggingInterceptor())
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .build()
    }

    @Throws(IOException::class)
    private fun defaultHTTPClient(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Throws(IOException::class)
    private fun serviceHTTPClient(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestUrl = original
                .url
                .newBuilder()
                .build()

            val requestBuilder = original.newBuilder().url(requestUrl)
                .build()
            return@Interceptor chain.proceed(requestBuilder)
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}