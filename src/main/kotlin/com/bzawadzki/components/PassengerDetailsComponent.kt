package com.bzawadzki.components

import com.bzawadzki.data.Passenger
import com.bzawadzki.data.Type
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class PassengerDetailsComponent(
        private val element: SelenideElement
) {
    fun fillData(passenger: Passenger) {
        element.find(By.xpath("./*//select[@ng-model='passenger.personal_details.gender']"))
                .selectOptionByValue(passenger.gender.name.toLowerCase())
        element.find(By.xpath("./*//input[@ng-model='passenger.personal_details.first_name']"))
                .value = passenger.name
        element.find(By.xpath("./*//input[@ng-model='passenger.personal_details.last_name']"))
                .value = passenger.last_name
        element.find(By.xpath("./*//date-input[@ng-model='passenger.personal_details.birth_date']//input[contains(@class, 'day-input')]"))
                .value = passenger.type.birthDate.dayOfMonth.toString()
        element.find(By.xpath("./*//date-input[@ng-model='passenger.personal_details.birth_date']//input[contains(@class, 'month-input')]"))
                .value = passenger.type.birthDate.monthValue.toString()
        element.find(By.xpath("./*//date-input[@ng-model='passenger.personal_details.birth_date']//input[contains(@class, 'year-input')]"))
                .value = passenger.type.birthDate.year.toString()
        if (passenger.type == Type.ADULT || passenger.type == Type.KID)
            element.find(By.xpath("./*//select[contains(@id, 'baggage-')]")).selectOption(1)
    }
}