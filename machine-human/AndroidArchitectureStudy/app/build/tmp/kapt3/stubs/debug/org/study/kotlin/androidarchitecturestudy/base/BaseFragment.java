package org.study.kotlin.androidarchitecturestudy.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u00052\u00020\u0006B\u000f\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ!\u0010\u0013\u001a\u00020\u00142\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140\u0016\u00a2\u0006\u0002\b\u0017H\u0004J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$H\u0016R\u001c\u0010\n\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00028\u0001X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006%"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseFragment;", "B", "Landroidx/databinding/ViewDataBinding;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseView;", "layoutId", "", "(I)V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "viewModel", "getViewModel", "()Landroidx/lifecycle/ViewModel;", "bind", "", "action", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showErrorToast", "errorMessage", "", "app_debug"})
public abstract class BaseFragment<B extends androidx.databinding.ViewDataBinding, VM extends androidx.lifecycle.ViewModel> extends androidx.fragment.app.Fragment implements org.study.kotlin.androidarchitecturestudy.base.BaseView {
    @org.jetbrains.annotations.NotNull()
    protected B binding;
    private final int layoutId = 0;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final B getBinding() {
        return null;
    }
    
    protected final void setBinding(@org.jetbrains.annotations.NotNull()
    B p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract VM getViewModel();
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void showErrorToast(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable errorMessage) {
    }
    
    protected final void bind(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super B, kotlin.Unit> action) {
    }
    
    public BaseFragment(@androidx.annotation.LayoutRes()
    int layoutId) {
        super();
    }
}