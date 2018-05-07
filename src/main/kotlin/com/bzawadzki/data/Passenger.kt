package com.bzawadzki.data

import com.beust.klaxon.Json
import java.time.LocalDate

data class Passenger(
        @Json(name = "gender") val gender: Gender = Gender.MALE,
        @Json(name = "name") val name: String = "default_name",
        @Json(name = "last_name") val last_name: String = "default_surname",
        @Json(name = "type") val type: Type = Type.ADULT
)

enum class Gender {
    MALE, FEMALE
}

enum class Type(val birthDate: LocalDate) {
    ADULT(LocalDate.now().minusYears(20)),
    KID(LocalDate.now().minusYears(10)),
    NEWBORN(LocalDate.now().minusYears(1))
}