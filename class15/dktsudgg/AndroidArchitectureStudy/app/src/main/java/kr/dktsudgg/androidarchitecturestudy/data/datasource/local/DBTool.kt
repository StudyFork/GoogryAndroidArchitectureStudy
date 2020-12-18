package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import javax.inject.Qualifier

interface DBTool {

    fun putData(prefFileKey: String, putKey: String, data: String)

    fun getData(prefFileKey: String, getKey: String): String

}

/**
 * 관계형 디비 객체 DI를 위한 애노테이션
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RDBTool

/**
 * Schema less 디비 객체 DI를 위한 애노테이션
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NosqlTool