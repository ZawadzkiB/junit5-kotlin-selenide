package com.bzawadzki.components

import com.bzawadzki.helper.SMALL_TIMEOUT
import com.bzawadzki.helper.clickUsingJS
import com.bzawadzki.page.PassengersPage
import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class FlightComponent(
        private val element: SelenideElement
) {
    private val bookingButtonLocator = "./..//button[contains(@ng-click, 'bookings/create')]"

    fun startPointElements(): ElementsCollection = element.findAll(By.xpath("./*//div[contains(@class, 'start')]//span[@class='airport-code']"))
            .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(2))
    fun destinationPointElements(): ElementsCollection = element.findAll(By.xpath("./*//div[contains(@class, 'destination')]//span[@class='airport-code']"))
            .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(2))

    fun expand(): FlightComponent {
        element.click()
        element.find(By.xpath("./div[@class='animate-container open']")).waitUntil(Condition.visible, SMALL_TIMEOUT)
        return this
    }

    fun selectFlight(): PassengersPage {
        clickUsingJS(element.find(By.xpath(bookingButtonLocator))
                .waitUntil(Condition.enabled, SMALL_TIMEOUT))
        return PassengersPage()
    }
}