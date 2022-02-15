package com.test.testoll.network

import com.test.testoll.data.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/api/demo")
    suspend fun getPrograms(
        @Query("serial_number") uuid: String,
        @Query("borderId") borderID: Int,
        @Query("direction") direction: Int
    ): PagedResponse
}