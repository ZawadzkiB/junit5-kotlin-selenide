package com.bzawadzki.helper

import com.codeborne.selenide.ex.UIAssertionError

fun <T> retry(attempts:Int = 3, function: () -> T): T {
    repeat(attempts - 1) {
        try {
            return function()
        }catch (e: UIAssertionError){
            println(e.message)
        }
    }
    return function()
}