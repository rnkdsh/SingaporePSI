package com.singaporepsi.api

import com.singaporepsi.model.ErrorResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("test")
        val errorMessage = ErrorResponse(400, "test").message
        assertThat<String>(errorMessage, `is`(exception.message))
    }

    @Test
    fun success() {
        val apiResponse: Response<String> = Response.success("test")
        assertThat<String>(apiResponse.body(), `is`("test"))
    }

    @Test
    fun error() {
        val errorResponse = Response.error<String>(
            400,
            "test".toResponseBody("application/txt".toMediaType())
        )
        val errorMessage = ErrorResponse(400, "test").message
        assertThat<String>(errorMessage, `is`("test"))
    }
}