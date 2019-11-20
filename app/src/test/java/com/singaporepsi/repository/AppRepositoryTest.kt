package com.singaporepsi.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.singaporepsi.TestUtil
import com.singaporepsi.api.ApiService
import com.singaporepsi.data.AppRepository
import com.singaporepsi.util.ApiUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class AppRepositoryTest {

    private val apiService = mock(ApiService::class.java)
    private val gson = Gson()
    private val repo = AppRepository(apiService, gson)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun callApi() {
        val psi = TestUtil.createSuccessResponse()
        val call = ApiUtil.successCall(psi)
        /*`when`(apiService!!.psi(hashMapOf())).thenReturn(call)

        repo.psi()
        verify(apiService, never()).psi(hashMapOf())*/
    }
}