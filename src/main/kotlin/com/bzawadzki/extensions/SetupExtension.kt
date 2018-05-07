package com.bzawadzki.extensions

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.WebDriverRunner.getWebDriver
import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.extension.*
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class SetupExtension : BeforeEachCallback, AfterEachCallback {

    override fun afterEach(context: ExtensionContext?) {
        getWebDriver().quit()
    }

    override fun beforeEach(context: ExtensionContext?) {
        WebDriverManager.chromedriver().setup()
        WebDriverRunner.setWebDriver(ChromeDriver())
        Configuration.timeout = TimeUnit.SECONDS.toMillis(15)
        Configuration.collectionsTimeout = TimeUnit.SECONDS.toMillis(15)
        Selenide.open("https://flighttix.pl/")
    }


}