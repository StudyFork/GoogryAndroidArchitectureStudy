object DataFormatUtills {
    fun setTradeVolumeText(market: String, accTradePrice24h: Double) =
        when (market.substringBefore("-")) {
            "KRW", "USDT" -> {
                var tradeVolume = accTradePrice24h.toLong()
                String.format(
                    when {
                        tradeVolume < 1_000_000L -> {
                            "%,d"
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            "%,d k"
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            "%,d M"
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            "%,d G"
                        }
                    }, tradeVolume
                )
            }
            "BTC", "ETH" -> {
                String.format(
                    "%,.3f"
                    , accTradePrice24h
                )
            }
            else -> {
                var tradeVolume: Long = accTradePrice24h.toLong()
                String.format(
                    when {
                        tradeVolume < 1_000L -> {
                            "%,.3f"

                        }
                        tradeVolume < 1_000_000L -> {
                            "%,d"
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            "%,d k"
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            "%,d M"
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            "%,d G"
                        }
                    }, tradeVolume
                )
            }
        }
}