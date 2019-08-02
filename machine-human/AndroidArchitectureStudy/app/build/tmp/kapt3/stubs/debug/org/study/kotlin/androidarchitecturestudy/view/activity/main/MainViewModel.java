package org.study.kotlin.androidarchitecturestudy.view.activity.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0007H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainViewModel;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseViewModel;", "tickerRepository", "Lorg/study/kotlin/androidarchitecturestudy/data/TickerRepository;", "(Lorg/study/kotlin/androidarchitecturestudy/data/TickerRepository;)V", "_observableErrorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "get_observableErrorMessage", "()Landroidx/lifecycle/MutableLiveData;", "_observableTickerList", "", "Lorg/study/kotlin/androidarchitecturestudy/api/model/TickerModel;", "get_observableTickerList", "observableErrorMessage", "Landroidx/lifecycle/LiveData;", "getObservableErrorMessage", "()Landroidx/lifecycle/LiveData;", "observableTickerList", "getObservableTickerList", "getTickerListFromRemoteDataSource", "", "marketName", "", "onDataNotAvailable", "errorMessage", "app_debug"})
public final class MainViewModel extends org.study.kotlin.androidarchitecturestudy.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> _observableTickerList = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Throwable> _observableErrorMessage = null;
    private final org.study.kotlin.androidarchitecturestudy.data.TickerRepository tickerRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.lifecycle.MutableLiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> get_observableTickerList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> getObservableTickerList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.lifecycle.MutableLiveData<java.lang.Throwable> get_observableErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Throwable> getObservableErrorMessage() {
        return null;
    }
    
    public final void getTickerListFromRemoteDataSource(@org.jetbrains.annotations.NotNull()
    java.lang.String marketName) {
    }
    
    @java.lang.Override()
    public void onDataNotAvailable(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable errorMessage) {
    }
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    org.study.kotlin.androidarchitecturestudy.data.TickerRepository tickerRepository) {
        super();
    }
}