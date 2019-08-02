package org.study.kotlin.androidarchitecturestudy.data;

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
 * f = onTickerListLoaded(tickerList: List<TickerModel>=
 * f = onDataNotAvailable(error: String)
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J>\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00070\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0010"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/data/TickerRepository;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseDataSource;", "tickerRemoteDataSource", "(Lorg/study/kotlin/androidarchitecturestudy/base/BaseDataSource;)V", "getTickerRemoteDataSource", "()Lorg/study/kotlin/androidarchitecturestudy/base/BaseDataSource;", "getTickerList", "", "marketName", "", "success", "Lkotlin/Function1;", "", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "failed", "", "app_debug"})
public final class TickerRepository implements org.study.kotlin.androidarchitecturestudy.base.BaseDataSource {
    @org.jetbrains.annotations.NotNull()
    private final org.study.kotlin.androidarchitecturestudy.base.BaseDataSource tickerRemoteDataSource = null;
    
    @java.lang.Override()
    public void getTickerList(@org.jetbrains.annotations.NotNull()
    java.lang.String marketName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>, kotlin.Unit> success, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> failed) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.study.kotlin.androidarchitecturestudy.base.BaseDataSource getTickerRemoteDataSource() {
        return null;
    }
    
    public TickerRepository(@org.jetbrains.annotations.NotNull()
    org.study.kotlin.androidarchitecturestudy.base.BaseDataSource tickerRemoteDataSource) {
        super();
    }
}