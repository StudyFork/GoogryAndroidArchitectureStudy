package com.example.kyudong3.provider

interface ResourceProvider {
    fun getString(resourceId: Int): String
}