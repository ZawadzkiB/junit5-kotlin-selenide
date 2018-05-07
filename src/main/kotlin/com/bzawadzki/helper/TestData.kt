package com.bzawadzki.helper

import com.beust.klaxon.Klaxon
import com.bzawadzki.data.Flight
import com.bzawadzki.data.Passenger

class TestData {

    fun getPassengers(): List<Passenger> {
        return Klaxon()
                .parseArray(this::class.java.classLoader.getResource("data//Passengers.json").readText())!!
    }

    fun getFlight(): Flight {
        return Klaxon()
                .parse(this::class.java.classLoader.getResource("data//Flight.json").readText())!!
    }
}