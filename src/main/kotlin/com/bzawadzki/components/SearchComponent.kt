package com.bzawadzki.components

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class SearchComponent(
        private val element: SelenideElement,
        private val suggestionLocator: String = "//div[(@id='autocomplete-scroll') and (not(contains(@class, 'in-active')))]//ul[contains(@class, 'ui-autocomplete')]"
) {
    fun search(searchText: String, type: SearchType) {
        element.sendKeys(searchText)
        clickFirstSuggestion(type)
    }

    fun searchAndSelectSuggestionWithText(searchText: String, type: SearchType) {
        element.sendKeys(searchText)
        clickSuggestionEqualsText(searchText, type)
    }

    private fun clickFirstSuggestion(type: SearchType) {
        `$$`(By.xpath(suggestionLocator))
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1))[type.indexInDOM]
                .findAll(By.xpath("./li"))[0]
                .shouldBe(Condition.visible).click()
    }

    private fun clickSuggestionEqualsText(text: String, type: SearchType) {
        `$$`(By.xpath(suggestionLocator))
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1))[type.indexInDOM]
                .findAll(By.xpath("./li"))
                .filter(Condition.exactText(text)).first()
                .shouldBe(Condition.visible).click()
    }

}

enum class SearchType(val indexInDOM: Int) {
    AIRLINE(4), FROM_WITH_SUGGESTION(0), TO_WITH_SUGGESTION(1)
}