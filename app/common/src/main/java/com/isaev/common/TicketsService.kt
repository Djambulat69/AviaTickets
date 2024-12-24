package com.isaev.common

import retrofit2.http.GET

interface TicketsService {
    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getMain(): MainResponse

    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getSearch()
}

