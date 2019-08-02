package org.study.kotlin.androidarchitecturestudy.data.source.remote;

import java.lang.System;

/**
 * ***************************
 * BaseDataSource - structure
 *
 * i = interface
 * f = function
 * ***************************
 *
 * i = BaseDataSource
 *
 * i = GetTickerListCallback
 *
 * f = onTickerListLoaded(tickerList: List<TickerModel>)
 * f = onDataNotAvailable(error: String)
 *
 * f = requestMarkets(marketName: String, callback: GetTickerListCallback)
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J>\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0018\u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\u00060\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00060\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/data/source/remote/TickerRemoteDataSource;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseDataSource;", "upbitApi", "Lorg/study/kotlin/androidarchitecturestudy/api/UpbitApi;", "(Lorg/study/kotlin/androidarchitecturestudy/api/UpbitApi;)V", "getTickerList", "", "marketName", "", "success", "Lkotlin/Function1;", "", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "failed", "", "app_debug"})
public final class TickerRemoteDataSource implements org.study.kotlin.androidarchitecturestudy.base.BaseDataSource {
    private final org.study.kotlin.androidarchitecturestudy.api.UpbitApi upbitApi = null;
    
    @java.lang.Override()
    public void getTickerList(@org.jetbrains.annotations.NotNull()
    java.lang.String marketName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>, kotlin.Unit> success, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> failed) {
    }
    
    public TickerRemoteDataSource(@org.jetbrains.annotations.NotNull()
    org.study.kotlin.androidarchitecturestudy.api.UpbitApi upbitApi) {
        super();
    }
}