package com.isaev.common

import retrofit2.http.GET

interface AllTicketsService {

    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getAllTickets(): AllTickets
}