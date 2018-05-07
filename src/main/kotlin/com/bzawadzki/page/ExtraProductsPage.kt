package com.bzawadzki.page

import com.bzawadzki.helper.SMALL_TIMEOUT
import com.bzawadzki.helper.clickUsingJS
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.amshove.kluent.shouldBe
import org.openqa.selenium.By

class ExtraProductsPage() {

    fun selectExtraProductWithValue(value: Int): ExtraProductsPage {
        clickUsingJS(`$`(By.xpath("//input[@value='%d']".format(value))).waitUntil(Condition.enabled, SMALL_TIMEOUT))
        return this
    }

    fun assertCheckoutButtonIsVisible() {
        `$`(By.xpath("//button[@ng-click='processAndSubmitExtraProducts()']")).isDisplayed shouldBe true
    }
}