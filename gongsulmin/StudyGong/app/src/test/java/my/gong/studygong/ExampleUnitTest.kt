package my.gong.studygong

import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse
import my.gong.studygong.data.network.RetrofitProvider
import my.gong.studygong.data.source.upbit.UpbitRepository
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        Thread.sleep(2000)

//        UpbitRepository.getMarket(
//                        {
//                            println("  ${it.toString()}")
//                        } ,
//
//                        {
//                            println("   ${it}     ")
//                        }
//
//        )

        RetrofitProvider.upbitRetrofit().getTicker("KRW-BTC,KRW-DASH").enqueue(object : retrofit2.Callback<List<UpbitTickerResponse>>{
            override fun onFailure(call: Call<List<UpbitTickerResponse>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<UpbitTickerResponse>>,
                response: Response<List<UpbitTickerResponse>>
            ) {
                println("    ${response.body()} ")
            }

        })

        Thread.sleep(2000)
    }
}
