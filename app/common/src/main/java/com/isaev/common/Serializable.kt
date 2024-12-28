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

@Serializable
data class AllTickets(
    val tickets: List<Ticket>
)

@Serializable
data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Price,
    @SerialName("provider_name") val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    @SerialName("has_transfer") val hasTransfer: Boolean,
    @SerialName("has_visa_transfer") val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerialName("hand_luggage") val handLuggage: HandLuggage,
    @SerialName("is_returnable") val isReturnable: Boolean,
    @SerialName("is_exchangable") val isExchangable: Boolean
)

@Serializable
data class Departure(
    val town: String,
    val date: String,
    val airport: String
)

@Serializable
data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)

@Serializable
data class Luggage(
    @SerialName("has_luggage") val hasLuggage: Boolean,
    val price: Price
)

@Serializable
data class HandLuggage(
    @SerialName("has_hand_luggage") val hasHandLuggage: Boolean,
    val size: String
)

