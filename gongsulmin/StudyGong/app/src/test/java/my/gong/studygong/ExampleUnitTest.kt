package my.gong.studygong

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.source.upbit.UpbitRepository
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//
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
//
//        UpbitRepository.getTickers("aa" , {
//
//        }  , {
//
//        })
        UpbitRepository.getTickers(
            {
                it.map {
                        Ticker(it.market)
                }
            },

            {
                println("  $it   ")
            }

        )


        Thread.sleep(2000)
    }
}
