package sample.nackun.com.studyfirst

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var a = 5
        var b = 0
        println("a = $a b = $b");
        a = a xor b
        b = a xor b
        a = a xor b
        //a = b or a
        //a = b - a
        println("a = $a b = $b");
    }
}
