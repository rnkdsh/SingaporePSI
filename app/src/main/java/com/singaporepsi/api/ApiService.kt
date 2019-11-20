package com.singaporepsi.api

import com.singaporepsi.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("v1/environment/psi")
    suspend fun psi(@QueryMap parameters: Map<String, String>): Response<ApiResponse>
}