package com.bzawadzki.page

import com.bzawadzki.components.FlightComponent
import com.bzawadzki.data.Flight
import com.bzawadzki.helper.LONG_TIMEOUT
import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import org.amshove.kluent.shouldEqual
import org.openqa.selenium.By

class FlightsListPage(
        private val firstRecommendedResult: FlightComponent = FlightComponent(`$`(By.xpath("//div[(contains(@class, 'search-result-item')) and (contains(@class, 'first')) and (contains(@class, 'recommended')) and (not(contains(@class, 'not-')))]"))
                .waitUntil(visible, LONG_TIMEOUT, 100))
) {
    private val airlinesNames = `$$`("span.airline-name").shouldHave(CollectionCondition.sizeGreaterThan(0))

    fun expandFlight(flightComponent: FlightComponent = firstRecommendedResult): FlightsListPage {
        flightComponent.expand()
        return this
    }

    fun selectFlight(flightComponent: FlightComponent = firstRecommendedResult): PassengersPage {
        return flightComponent.selectFlight()
    }

    fun assertFlightsDestinationsAreCorrect(flight: Flight = Flight(), flightComponent: FlightComponent = firstRecommendedResult): FlightsListPage {
        flightComponent.startPointElements()[0].text shouldEqual flight.from
        flightComponent.destinationPointElements()[0].text shouldEqual flight.to
        flightComponent.startPointElements()[1].text shouldEqual flight.to
        flightComponent.destinationPointElements()[1].text shouldEqual flight.from
        return this
    }

    fun assertAirlinesNamesContains(airline: String) {
        airlinesNames.forEach { it.text() shouldEqual airline }
    }

}
