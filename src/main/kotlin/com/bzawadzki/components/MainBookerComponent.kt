package com.bzawadzki.components

import com.bzawadzki.data.MainBooker
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class MainBookerComponent(
        private val element: SelenideElement
) {
    private val countryLocator = By.xpath("./*//select[@id='booker_country_code']")
    private val streetLocator = ".street-name input"
    private val streetNumberLocator = By.xpath("./*//input[contains(@class, 'input-street-number-right')]")
    private val postalCodeLocator = By.xpath("./*//input[contains(@class, 'input-postal-code')]")
    private val cityLocator = By.xpath("./*//input[contains(@class, 'input-city')]")
    private val phoneLocator = By.xpath("./*//input[contains(@class, 'input-phone')]")
    private val emailLocator = By.xpath("./*//input[contains(@class, 'input-email')]")

    fun fillMainBookerContactData(mainBooker: MainBooker) {
        element.find(countryLocator).selectOptionByValue(mainBooker.country)
        element.find(streetNumberLocator).sendKeys(mainBooker.streetNumber)
        element.find(streetLocator).sendKeys(mainBooker.street)
        element.find(postalCodeLocator).sendKeys(mainBooker.postal_code)
        element.find(cityLocator).sendKeys(mainBooker.city)
        element.find(phoneLocator).sendKeys(mainBooker.phone)
        element.find(emailLocator).sendKeys(mainBooker.email)
    }

}