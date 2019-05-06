package my.gong.studygong.mvp

import my.gong.studygong.data.source.upbit.UpbitRepository

object Injection {
    fun provideCoinRepository(): UpbitRepository{
        return UpbitRepository
    }
}