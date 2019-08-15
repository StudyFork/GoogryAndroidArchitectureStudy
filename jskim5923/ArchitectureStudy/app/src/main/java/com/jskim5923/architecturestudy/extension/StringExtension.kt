package com.jskim5923.architecturestudy.extension

fun String.getCoinCurrency() = substringBefore("-")

fun String.getCoinName() = substringAfter("-")


