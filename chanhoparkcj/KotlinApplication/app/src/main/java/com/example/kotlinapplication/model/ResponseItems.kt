package com.example.kotlinapplication.model


data class ResponseItems<T>(
    val items: List<T>
)