package com.example.architecturestudy.util

object Filter {
    enum class Type {
        MARKET, TRADE_PRICE, SIGNED_CHANGED_RATE, ACC_TRADE_PRICE_H
    }

    enum class Order {
        ASC, DESC
    }
}