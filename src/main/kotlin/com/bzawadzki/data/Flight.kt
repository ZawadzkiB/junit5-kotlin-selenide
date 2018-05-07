package com.bzawadzki.data

import com.beust.klaxon.Json
import java.time.LocalDate

data class Flight(
        @Json(name = "from") val from: String = "AMS",
        @Json(name = "to") val to: String = "MAD",
        val departureDate: LocalDate = LocalDate.now().plusDays(10),
        val returnDate: LocalDate = LocalDate.now().plusDays(20)
)