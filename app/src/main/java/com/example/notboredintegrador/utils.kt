package com.example.notboredintegrador

/**
 * Returns a string equal to the string received but with the first char uppercase
 * Returns an empty string if text is null
 * */
fun capitalize(text: String?): String {
    if(text.isNullOrBlank()) return ""

    val newText = text.toCharArray()
    newText[0] = text[0].uppercaseChar()
    return newText.concatToString()
}