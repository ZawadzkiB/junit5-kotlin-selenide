package com.bzawadzki.page

import com.bzawadzki.components.*
import com.bzawadzki.data.Flight
import com.bzawadzki.data.Passenger
import com.bzawadzki.data.Type
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import java.time.LocalDate

class HomePage(
        private val searchFrom: SearchComponent = SearchComponent(`$`("#flight-from-main-search-box-f").shouldBe(visible)),
        private val searchTo: SearchComponent = SearchComponent(`$`("#flight-to-main-search-box-f").shouldBe(visible)),
        private val datePicker: DatePickerComponent = DatePickerComponent(`$`(".date-picker-group").shouldBe(visible))
) {
    private val selectAdultPassengers: PassengersSelectComponent = PassengersSelectComponent(`$`(By.xpath("//span[contains(@id, 'flight-passenger-type-0')]")))
    private val selectKidsPassengers: PassengersSelectComponent = PassengersSelectComponent(`$`(By.xpath("//span[contains(@id, 'flight-passenger-type-1')]")))
    private val selectNewbornPassengers: PassengersSelectComponent = PassengersSelectComponent(`$`(By.xpath("//span[contains(@id, 'flight-passenger-type-2')]")))
    private val submitButton: ElementsCollection? = `$$`(By.xpath("//button[@name='submit']"))
    private val advancedSearchToggle = `$`(".advanced-search-toggle[tabindex]")
    private val airlineInput = SearchComponent(`$`(By.id("flight-airline-left-panel-search-box-f")))

    fun fillFlightData(flight: Flight = Flight(), passengers: List<Passenger> = listOf(Passenger())): HomePage {
        searchFlightFromTo(flight.from, flight.to)
                .setDates(flight.departureDate, flight.returnDate)
                .selectPassengers(
                        adults = passengers.filter { it.type == Type.ADULT }.size,
                        kids = passengers.filter { it.type == Type.KID }.size,
                        newborn = passengers.filter { it.type == Type.NEWBORN }.size
                )
        return this
    }

    fun advancedSearch(): HomePage {
        advancedSearchToggle.click()
        return this
    }

    fun fillAirLine(airline: String): HomePage {
        airlineInput.searchAndSelectSuggestionWithText(airline, SearchType.AIRLINE)
        return this
    }

    fun submit(): FlightsListPage {
        submitButton!!.filter(visible).first().click()
        return FlightsListPage()
    }

    private fun searchFlightFromTo(from: String, to: String): HomePage {
        searchFrom.search(from, SearchType.FROM_WITH_SUGGESTION)
        searchTo.search(to, SearchType.TO_WITH_SUGGESTION)
        return this
    }

    private fun setDates(departureDate: LocalDate, returnDate: LocalDate): HomePage {
        datePicker.selectDateFor(DatePickerType.DEPARTURE, departureDate)
        datePicker.selectDateFor(DatePickerType.RETURN, returnDate)
        return this
    }

    private fun selectPassengers(adults: Int = 1,
                                 kids: Int = 0,
                                 newborn: Int = 0): HomePage {
        selectAdultPassengers.selectByText(adults.toString())
        selectKidsPassengers.selectByText(kids.toString())
        selectNewbornPassengers.selectByText(newborn.toString())
        return this
    }
}