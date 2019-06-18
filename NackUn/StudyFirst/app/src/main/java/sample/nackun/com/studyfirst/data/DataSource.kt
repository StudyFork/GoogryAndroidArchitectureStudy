package sample.nackun.com.studyfirst.data

interface DataSource {
    fun requestMarkets(marketLike: String)
    fun requestTickers(query: String)
}