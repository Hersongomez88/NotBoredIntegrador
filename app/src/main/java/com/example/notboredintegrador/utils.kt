package com.example.notboredintegrador

/**
 * Returns a string equal to the string received but with the first char uppercase
 * */
fun capitalize(text: String): String {
    val newText = text.toCharArray()
    newText[0] = text[0].uppercaseChar()
    return newText.concatToString()
}