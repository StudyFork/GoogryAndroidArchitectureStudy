package kr.dktsudgg.androidarchitecturestudy

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `list to string, string to list 테스트`(){
        var testList = listOf<String>("테스트1", "테스트2", "테스트3 띄워쓰기포함", "테스트4")
        var resultStr: String = testList.joinToString (prefix = "", separator = "|||", postfix = "")
        var resultList: List<String> = resultStr.split("|||").map { it }
        for(item in resultList){
            println(item)
        }

    }
}