package org.wit.placemark.helpers

object ValidationHelper {
    fun isTextValid(text: String): Boolean {
        val regex = "^[a-zA-Z\\s]+$".toRegex()
        return text.matches(regex)
    }

    fun isRatingValid(input: Int): Boolean {
        return input in 1..5
    }
}