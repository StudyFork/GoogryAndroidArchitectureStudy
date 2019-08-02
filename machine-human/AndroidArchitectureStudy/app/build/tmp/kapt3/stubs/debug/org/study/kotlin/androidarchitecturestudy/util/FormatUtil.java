package org.study.kotlin.androidarchitecturestudy.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0015"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/util/FormatUtil;", "", "()V", "format", "Landroid/icu/text/DecimalFormat;", "getFormat", "()Landroid/icu/text/DecimalFormat;", "accTradePriceFormat", "", "number", "", "market", "commaFormat", "", "convertTo", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "tickerList", "floatingEightPointFormat", "floatingThreePointFormat", "percentFormat", "usdtFloatingPointFormat", "app_debug"})
public final class FormatUtil {
    @org.jetbrains.annotations.NotNull()
    private static final android.icu.text.DecimalFormat format = null;
    public static final org.study.kotlin.androidarchitecturestudy.util.FormatUtil INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.icu.text.DecimalFormat getFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String commaFormat(@org.jetbrains.annotations.NotNull()
    java.lang.Number number) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String floatingEightPointFormat(double number) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String floatingThreePointFormat(double number) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String usdtFloatingPointFormat(double number) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String percentFormat(double number) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String accTradePriceFormat(double number, @org.jetbrains.annotations.NotNull()
    java.lang.String market) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.study.kotlin.androidarchitecturestudy.api.model.TickerModel convertTo(@org.jetbrains.annotations.NotNull()
    org.study.kotlin.androidarchitecturestudy.api.model.TickerModel tickerList) {
        return null;
    }
    
    private FormatUtil() {
        super();
    }
}