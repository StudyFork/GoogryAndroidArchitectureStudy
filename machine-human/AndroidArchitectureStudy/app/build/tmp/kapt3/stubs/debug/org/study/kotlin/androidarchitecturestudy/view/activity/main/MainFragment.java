package org.study.kotlin.androidarchitecturestudy.view.activity.main;

import java.lang.System;

/**
 * ***************************
 * Contract - structure
 *
 * i = interface
 * f = function
 * v = variable
 * ***************************
 *
 * i = MainContract
 *
 * i = View : BaseView<Presenter>
 * f = setTickers(tickers: List<TickerModel>)
 *
 * i = Presenter : BasePresenter
 *
 * f = requestDataFromTickerRepository(marketName: String)
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001b\u0010\u0005\u001a\u00020\u00038VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainFragment;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseFragment;", "Lorg/study/kotlin/androidarchitecturestudy/databinding/FragmentMainBinding;", "Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainViewModel;", "()V", "viewModel", "getViewModel", "()Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "showErrorToast", "errorMessage", "", "Companion", "app_debug"})
public final class MainFragment extends org.study.kotlin.androidarchitecturestudy.base.BaseFragment<org.study.kotlin.androidarchitecturestudy.databinding.FragmentMainBinding, org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    public static final org.study.kotlin.androidarchitecturestudy.view.activity.main.MainFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void showErrorToast(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable errorMessage) {
    }
    
    public MainFragment() {
        super(0);
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainFragment$Companion;", "", "()V", "createInstance", "Lorg/study/kotlin/androidarchitecturestudy/view/activity/main/MainFragment;", "message", "", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final org.study.kotlin.androidarchitecturestudy.view.activity.main.MainFragment createInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}