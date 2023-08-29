package com.example.rijksdataexplorer.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("key", "0fiuZFh4")
            .build()

        request = request
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}