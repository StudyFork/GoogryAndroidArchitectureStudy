package org.study.kotlin.androidarchitecturestudy.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001e\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'\u00a8\u0006\n"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/api/UpbitApi;", "", "getMarket", "Lio/reactivex/Single;", "", "Lorg/study/kotlin/androidarchitecturestudy/api/model/MarketModel;", "getTicker", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "markets", "", "app_debug"})
public abstract interface UpbitApi {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "v1/market/all")
    public abstract io.reactivex.Single<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.MarketModel>> getMarket();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "v1/ticker")
    public abstract io.reactivex.Single<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> getTicker(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "markets")
    java.lang.String markets);
}