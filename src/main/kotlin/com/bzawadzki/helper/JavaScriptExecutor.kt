package com.bzawadzki.helper

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement

fun clickUsingJS(element: SelenideElement) {
    Selenide.executeJavaScript<SelenideElement>("arguments[0].click();", element)
}