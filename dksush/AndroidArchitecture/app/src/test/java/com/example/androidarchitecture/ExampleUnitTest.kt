package com.example.androidarchitecture

import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import android.R.attr.tag
import java.util.*
import java.util.Arrays.asList
import android.R.attr.tag
import android.R.attr.tag


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        data class Person (var name : String = "Anonymous", var age : Int = 0 , var gender : String = "사람" )


        val person = Person(age = 27 , gender = "남자" , name = "YunJin Choi")
        val (이름 , 나이 , 성별 ) = person
        //println("${나이} :: ${이름} :: ${성별}")



//        Flowable.just("dksush").subscribe{ println(it) }
//        val valueList = asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//        for (data in valueList) {
//            if (data % 2 == 0) {
//                val result = "value $data"
//                println(result)
//            }
//        }
//
//
//        Flowable.fromIterable(valueList).
//                filter{(it%2)==0}.
//                subscribe { print(it) }
//
//
//        val var1 : Double = 123.123
//        println(var1)
//        var1 as? String
//        print(var1)

    }
}
