package com.example.rijksdataexplorer.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class FrequencyRestrictingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val maxAgeSeconds = 1800
        val response = chain.proceed(chain.request())

        return response
            .newBuilder()
            .header("Cache-Control", "public, max-age=$maxAgeSeconds")
            .removeHeader("Pragma")
            .build()
    }
}