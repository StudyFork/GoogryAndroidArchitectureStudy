package kr.dktsudgg.androidarchitecturestudy.api

import javax.inject.Qualifier
import kotlin.reflect.KClass

interface NetworkRequestManager {

    fun <T : Any> getClient(serviceInterface: KClass<T>): T?

}

/**
 * Http 통신 처리 객체 DI를 위한 애노테이션 선언
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StudyHttpRequest

/**
 * TCP 통신 처리 객체 DI를 위한 애노테이션 선언
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StudyTcpRequest