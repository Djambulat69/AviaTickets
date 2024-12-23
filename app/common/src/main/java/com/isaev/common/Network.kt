package com.isaev.common

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object Network {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.get("application/json"))
        )
        .baseUrl("https://drive.usercontent.google.com/u/0/")
        .build()

    private val service = retrofit.create(TicketsService::class.java)


    suspend fun getMain(): List<Offer> = service.getMain().offers
}