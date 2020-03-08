package io.github.sooakim.data.local.source

interface SAAuthLocalDataSource {
    val isAuthRequired: Boolean

    fun saveAuth()
}