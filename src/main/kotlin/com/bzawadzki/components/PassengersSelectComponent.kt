package com.bzawadzki.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class PassengersSelectComponent(val element: SelenideElement) {

    fun selectByText(text: String){
        element.click()
        element.sendKeys(text)
        `$`(By.xpath("//li[contains(@class, 'needsclick') and contains(@class, 'ui-state-focu')]"))
        element.pressEnter()
    }
}