package com.bzawadzki.components

import com.bzawadzki.helper.clickUsingJS
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import java.time.LocalDate

class DatePickerComponent(
        private val selenideElement: SelenideElement
) {
    private val datepickerLayoutLocator = "//div[(contains(@datepicker-current-field-id, 'flight-%s-date')) and ((contains(@class, 'active')) and (contains(@class, 'open')))]"
    private val closeLocator = "//div[(contains(@datepicker-current-field-id, 'flight-')) and ((contains(@class, 'active')) and (contains(@class, 'open')))]//a[@class='close']"

    fun selectDateFor(dateType: DatePickerType, date: LocalDate) {
        selenideElement.find(".%s-date".format(dateType.type))
                .shouldBe(visible).click()

        `$`(By.xpath(datepickerLayoutLocator.format(dateType.type)))
                .shouldBe(visible)

        clickUsingJS(`$`(By.xpath("//a[text()='%d']/parent::td[(@data-month='%d') and (@data-year='%d')]"
                .format(date.dayOfMonth, date.monthValue - 1, date.year))))

        if (`$`(By.xpath(closeLocator)).`is`(visible))
            `$`(By.xpath(closeLocator)).click()
    }
}

enum class DatePickerType(val type: String) {
    DEPARTURE("departure"), RETURN("return")
}