package my.gong.studygong

import my.gong.studygong.data.source.upbit.UpbitRepository

object Injection {
    fun provideCoinRepository(): UpbitRepository {
        return UpbitRepository
    }
}