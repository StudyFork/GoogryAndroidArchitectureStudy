package org.study.kotlin.androidarchitecturestudy.api.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003Ja\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000e\u00a8\u0006+"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "", "accTradePrice24h", "", "changeRate", "market", "", "tradePrice", "convertAccTradePrice24h", "convertChangeRate", "convertMarket", "convertTradePrice", "(DDLjava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccTradePrice24h", "()D", "getChangeRate", "getConvertAccTradePrice24h", "()Ljava/lang/String;", "setConvertAccTradePrice24h", "(Ljava/lang/String;)V", "getConvertChangeRate", "setConvertChangeRate", "getConvertMarket", "setConvertMarket", "getConvertTradePrice", "setConvertTradePrice", "getMarket", "getTradePrice", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class TickerModel {
    @com.google.gson.annotations.SerializedName(value = "acc_trade_price_24h")
    private final double accTradePrice24h = 0.0;
    @com.google.gson.annotations.SerializedName(value = "change_rate")
    private final double changeRate = 0.0;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "market")
    private final java.lang.String market = null;
    @com.google.gson.annotations.SerializedName(value = "trade_price")
    private final double tradePrice = 0.0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String convertAccTradePrice24h;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String convertChangeRate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String convertMarket;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String convertTradePrice;
    
    public final double getAccTradePrice24h() {
        return 0.0;
    }
    
    public final double getChangeRate() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarket() {
        return null;
    }
    
    public final double getTradePrice() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConvertAccTradePrice24h() {
        return null;
    }
    
    public final void setConvertAccTradePrice24h(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConvertChangeRate() {
        return null;
    }
    
    public final void setConvertChangeRate(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConvertMarket() {
        return null;
    }
    
    public final void setConvertMarket(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConvertTradePrice() {
        return null;
    }
    
    public final void setConvertTradePrice(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public TickerModel(double accTradePrice24h, double changeRate, @org.jetbrains.annotations.NotNull()
    java.lang.String market, double tradePrice, @org.jetbrains.annotations.Nullable()
    java.lang.String convertAccTradePrice24h, @org.jetbrains.annotations.Nullable()
    java.lang.String convertChangeRate, @org.jetbrains.annotations.Nullable()
    java.lang.String convertMarket, @org.jetbrains.annotations.Nullable()
    java.lang.String convertTradePrice) {
        super();
    }
    
    public final double component1() {
        return 0.0;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.study.kotlin.androidarchitecturestudy.api.model.TickerModel copy(double accTradePrice24h, double changeRate, @org.jetbrains.annotations.NotNull()
    java.lang.String market, double tradePrice, @org.jetbrains.annotations.Nullable()
    java.lang.String convertAccTradePrice24h, @org.jetbrains.annotations.Nullable()
    java.lang.String convertChangeRate, @org.jetbrains.annotations.Nullable()
    java.lang.String convertMarket, @org.jetbrains.annotations.Nullable()
    java.lang.String convertTradePrice) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}