package com.isaev.common

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

