package com.isaev.common

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object Network {
    private val jsonConverter = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(MediaType.get("application/json"))

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(jsonConverter)
        .baseUrl("https://drive.usercontent.google.com/u/0/")
        .build()


    private val allTicketsRetrofit = Retrofit.Builder()
        .addConverterFactory(jsonConverter)
        .baseUrl("https://drive.google.com/")
        .build()

    private val ticketsService: TicketsService = retrofit.create(TicketsService::class.java)
    private val allTicketsService: AllTicketsService =
        allTicketsRetrofit.create(AllTicketsService::class.java)


    suspend fun getMain(): List<Offer> = ticketsService.getMain().offers

    suspend fun getFirstTickets(): List<TicketOffer> = ticketsService.getFirstTickets().ticketOffers

    suspend fun getAllTickets(): List<Ticket> = allTicketsService.getAllTickets().tickets
}