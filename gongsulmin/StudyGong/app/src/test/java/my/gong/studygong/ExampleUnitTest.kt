package my.gong.studygong

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
        println(" dksdha??   ")
        UpbitRepository.getTickers(
            {
                println("   코인 데이터    $it")
            },
            {
                println(" 실패??     ${it}  ")

            })

        Thread.sleep(2000)
    }
}
