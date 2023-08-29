package com.example.rijksdataexplorer.core.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface SafeApiCallRepository {

    suspend fun <T : Any> apiCall(
        call: suspend () -> Response<T>
    ): Result<T> = runCatching {
        withContext(Dispatchers.IO) {
            call.invoke().body() ?: throw Exception()
        }
    }
}