package app.ch.study.data.remote.parse

interface Authorization {
    fun getClientToken(): String
    fun getClientSecret(): String
}