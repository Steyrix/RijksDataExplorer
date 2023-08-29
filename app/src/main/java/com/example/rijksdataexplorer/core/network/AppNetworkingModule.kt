package com.example.rijksdataexplorer.core.network

import android.content.Context
import com.example.rijksdataexplorer.core.network.interceptor.CacheReadingInterceptor
import com.example.rijksdataexplorer.core.network.interceptor.FrequencyRestrictingInterceptor
import com.example.rijksdataexplorer.core.network.interceptor.QueryParameterInterceptor
import com.example.rijksdataexplorer.core.data.repository.RijksDataService
import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import com.example.rijksdataexplorer.core.data.repository.RijksRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppNetworkingModule {

    @Provides
    @Singleton
    fun provideRijksRepository(
        service: RijksDataService
    ): RijksRepository {
        return RijksRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideRijksDataService(
        retrofit: Retrofit
    ): RijksDataService {
        return retrofit.create(RijksDataService::class.java)
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/en/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        context: Context,
        cacheReadingInterceptor: CacheReadingInterceptor
    ): OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient()
            .newBuilder()
            .addInterceptor(QueryParameterInterceptor())
            .addInterceptor(cacheReadingInterceptor)
            .addInterceptor(FrequencyRestrictingInterceptor())
            .addInterceptor(logger)
            .cache(cache)
            .build()
    }

    // TODO: Maybe try inject constructor
    @Provides
    fun provideCacheReadingInterceptor(
        context: Context
    ): CacheReadingInterceptor {
        return CacheReadingInterceptor(context)
    }
}