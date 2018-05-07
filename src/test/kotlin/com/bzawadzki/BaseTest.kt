package com.bzawadzki

import com.bzawadzki.annotations.Open
import com.bzawadzki.data.Flight
import com.bzawadzki.data.Passenger
import com.bzawadzki.extensions.SetupExtension
import com.bzawadzki.helper.TestData
import com.bzawadzki.page.FlightsListPage
import com.bzawadzki.page.HomePage
import org.junit.jupiter.api.extension.ExtendWith
import java.util.stream.Stream

@Open
@ExtendWith(SetupExtension::class)
class BaseTest {
    companion object {

        @JvmStatic
        fun passengers(): Stream<List<Passenger>> {
            return Stream.of(TestData().getPassengers())
        }

        @JvmStatic
        fun airlines(): Stream<String> {
            return Stream.of("Lufthansa", "KLM")
        }
    }

    fun searchFlight(flight: Flight = Flight(), passengers: List<Passenger>): FlightsListPage {
        return HomePage().fillFlightData(passengers = passengers).submit()
    }
}