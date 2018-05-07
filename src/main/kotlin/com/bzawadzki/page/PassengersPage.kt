package com.bzawadzki.page

import com.bzawadzki.components.MainBookerComponent
import com.bzawadzki.components.PassengerDetailsComponent
import com.bzawadzki.data.MainBooker
import com.bzawadzki.data.Passenger
import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By

class PassengersPage(
        private val passengersList: ElementsCollection = `$$`(By.xpath("//div[contains(@ng-repeat,'passengers')]"))
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1)),
        private val mainBooker: MainBookerComponent = MainBookerComponent(`$`(By.xpath("//div[@class='section main-booker']")))
) {

    fun fillPassengersData(passengers: List<Passenger>): PassengersPage {
        var passengerIndex = 0
        passengers.forEach {
            PassengerDetailsComponent(passengersList[passengerIndex]).fillData(it)
            passengerIndex = passengerIndex.inc()
        }
        return this
    }

    fun fillMainBookerData(mainBooker: MainBooker = MainBooker()): PassengersPage {
        this.mainBooker.fillMainBookerContactData(mainBooker)
        return this
    }

    fun submitPassengersData(): ExtraProductsPage {
        `$`(By.xpath("//div[@class='section section-submit']//button")).click()
        return ExtraProductsPage()
    }

}