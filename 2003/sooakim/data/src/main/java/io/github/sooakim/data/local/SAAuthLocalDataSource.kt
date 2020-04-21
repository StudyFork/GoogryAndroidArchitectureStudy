package io.github.sooakim.data.local

interface SAAuthLocalDataSource {
    val isAuthRequired: Boolean

    fun saveAuth()
}