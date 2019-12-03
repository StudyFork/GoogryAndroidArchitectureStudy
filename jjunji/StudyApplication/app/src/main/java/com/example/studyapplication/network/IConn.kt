package com.example.studyapplication.network

interface IConn {
    fun <T> success(result : T)
    fun failed()
}