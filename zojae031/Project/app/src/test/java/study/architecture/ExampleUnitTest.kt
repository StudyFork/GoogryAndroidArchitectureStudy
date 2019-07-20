package study.architecture

import org.junit.Test
import study.architecture.model.DataParser

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        DataParser.parseTickerList()
    }
}
