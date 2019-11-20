package com.singaporepsi.data

import com.google.gson.Gson
import com.singaporepsi.model.ErrorResponse
import com.singaporepsi.result.Result
import retrofit2.Response

open class BaseRepository constructor(val gson: Gson) {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return safeApiResult(call)
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            when {
                response.isSuccessful -> Result.Success(response.body()!!)
                else -> {
                    val errorResponse = try {
                        gson.fromJson<ErrorResponse>(
                            response.body().toString(),
                            ErrorResponse::class.java
                        )
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                        null
                    }
                    Result.Error(
                        errorResponse ?: ErrorResponse(
                            response.code(),
                            getErrorMessage(response.code())
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(
                ErrorResponse(
                    0,
                    getErrorMessage(0)
                )
            )
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            504 -> "Unable to connect to server"
            else -> "Something went wrong!"
        }
    }
}