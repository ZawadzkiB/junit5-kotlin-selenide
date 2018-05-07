package com.bzawadzki.tests

import com.bzawadzki.BaseTest
import com.bzawadzki.data.Passenger
import com.bzawadzki.page.HomePage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SearchFlightTest : BaseTest() {

    @ParameterizedTest
    @MethodSource("passengers")
    @DisplayName("search and book flight")
    fun searchAndBookFlight(passengers: List<Passenger>) {
        searchFlight(passengers = passengers)
                .expandFlight()
                .assertFlightsDestinationsAreCorrect()
                .selectFlight()
                .fillPassengersData(passengers)
                .fillMainBookerData()
                .submitPassengersData()
                .selectExtraProductWithValue(1643)
                .selectExtraProductWithValue(1666)
                .assertCheckoutButtonIsVisible()
    }

    @ParameterizedTest
    @MethodSource("airlines")
    @DisplayName("search for flights on concrete airline")
    fun flightsListShouldContainOnlySearchedAirlines(airline: String) {
        HomePage()
                .fillFlightData()
                .advancedSearch()
                .fillAirLine(airline)
                .submit()
                .assertAirlinesNamesContains(airline)
    }

}