package com.example.studyapplication.network

interface Conn {
    fun <T> success(result : T)
    fun failed(errorMessage : String)
}