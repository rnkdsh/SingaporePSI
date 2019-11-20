package com.singaporepsi.data

import com.google.gson.Gson
import com.singaporepsi.api.ApiService
import com.singaporepsi.model.ApiResponse
import com.singaporepsi.result.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppRepository @Inject constructor(
    private val apiService: ApiService,
    gson: Gson
) : BaseRepository(gson) {

    suspend fun psi(): Result<ApiResponse> {
        return safeApiCall(call = { apiService.psi(hashMapOf()) })
    }

    suspend fun psiForDate(date: String): Result<ApiResponse> {
        return safeApiCall(call = { apiService.psi(hashMapOf("date" to date)) })
    }

    suspend fun psiForTime(time: String): Result<ApiResponse> {
        return safeApiCall(call = { apiService.psi(hashMapOf("date_time" to time)) })
    }
}