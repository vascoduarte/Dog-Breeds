package com.outdoors.dogbreeds.util

fun String.showPrettyName():String{
    val words = split(" ")
    var str = ""
    words.forEach {
        str += it.capitalize() + " "
    }

    return str
}