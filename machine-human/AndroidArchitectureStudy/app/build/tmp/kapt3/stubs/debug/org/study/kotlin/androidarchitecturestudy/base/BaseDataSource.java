package org.study.kotlin.androidarchitecturestudy.base;

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
 *    i = GetTickerListCallback
 *
 *        f = onTickerListLoaded(tickerList: List<TickerModel>)
 *        f = onDataNotAvailable(error: String)
 *
 *    f = requestMarkets(marketName: String, callback: GetTickerListCallback)
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J>\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&\u00a8\u0006\f"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseDataSource;", "", "getTickerList", "", "marketName", "", "success", "Lkotlin/Function1;", "", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "failed", "", "app_debug"})
public abstract interface BaseDataSource {
    
    public abstract void getTickerList(@org.jetbrains.annotations.NotNull()
    java.lang.String marketName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>, kotlin.Unit> success, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> failed);
}