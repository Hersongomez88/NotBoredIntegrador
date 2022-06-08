package com.example.notboredintegrador


fun capitalize(text: String): String{
    val newText = text.toCharArray()
    newText[0] = text[0].uppercaseChar()
    return newText.concatToString()
}