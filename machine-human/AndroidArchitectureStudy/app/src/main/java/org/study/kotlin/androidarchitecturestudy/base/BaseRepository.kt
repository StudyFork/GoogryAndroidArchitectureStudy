package org.study.kotlin.androidarchitecturestudy.base

import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel

interface BaseRepository {
    fun getTickerList(marketName: String, success: (List<TickerModel>) -> Unit, failed: (Throwable) -> Unit)
}