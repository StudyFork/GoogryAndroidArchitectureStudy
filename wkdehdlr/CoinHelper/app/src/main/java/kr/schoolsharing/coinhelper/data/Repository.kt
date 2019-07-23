package kr.schoolsharing.coinhelper.data

import kr.schoolsharing.coinhelper.network.ApiConnector

class Repository {

    val apiConnector: ApiConnector = ApiConnector()

    fun getTickerFromLocal(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    fun getTickerFromApi(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}