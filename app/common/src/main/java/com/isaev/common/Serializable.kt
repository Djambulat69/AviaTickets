package com.isaev.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    val offers: List<Offer>
)

@Serializable
data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)

@Serializable
data class Price(
    val value: Int
)

@Serializable
data class TicketOffers(
    @SerialName("tickets_offers") val ticketOffers: List<TicketOffer>
)

@Serializable
data class TicketOffer(
    val id: Int,
    val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    val price: Price
)



