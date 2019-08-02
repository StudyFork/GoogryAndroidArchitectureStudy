package org.study.kotlin.androidarchitecturestudy.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseRecyclerView;", "", "()V", "Adapter", "ViewHolder", "app_debug"})
public abstract class BaseRecyclerView {
    
    public BaseRecyclerView() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00060\u0005B\u001b\u0012\b\b\u0003\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\bH\u0016J\u001e\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u0016\u0010\u0019\u001a\u00020\u00122\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001aR\u0012\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseRecyclerView$Adapter;", "ITEM", "", "B", "Landroidx/databinding/ViewDataBinding;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/study/kotlin/androidarchitecturestudy/base/BaseRecyclerView$ViewHolder;", "layoutRes", "", "bindingVariableId", "(ILjava/lang/Integer;)V", "Ljava/lang/Integer;", "items", "", "getItems", "()Ljava/util/List;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "replaceAll", "", "app_debug"})
    public static abstract class Adapter<ITEM extends java.lang.Object, B extends androidx.databinding.ViewDataBinding> extends androidx.recyclerview.widget.RecyclerView.Adapter<org.study.kotlin.androidarchitecturestudy.base.BaseRecyclerView.ViewHolder<? extends B>> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<ITEM> items = null;
        private final int layoutRes = 0;
        private final java.lang.Integer bindingVariableId = null;
        
        @org.jetbrains.annotations.NotNull()
        protected final java.util.List<ITEM> getItems() {
            return null;
        }
        
        public final void replaceAll(@org.jetbrains.annotations.Nullable()
        java.util.List<? extends ITEM> items) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.study.kotlin.androidarchitecturestudy.base.BaseRecyclerView.ViewHolder<B> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        org.study.kotlin.androidarchitecturestudy.base.BaseRecyclerView.ViewHolder<? extends B> holder, int position) {
        }
        
        public Adapter(@androidx.annotation.LayoutRes()
        int layoutRes, @org.jetbrains.annotations.Nullable()
        java.lang.Integer bindingVariableId) {
            super();
        }
        
        public Adapter() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B#\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u0013\u0010\n\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseRecyclerView$ViewHolder;", "B", "Landroidx/databinding/ViewDataBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "layoutRes", "", "parent", "Landroid/view/ViewGroup;", "bindingVariableId", "(ILandroid/view/ViewGroup;Ljava/lang/Integer;)V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "Landroidx/databinding/ViewDataBinding;", "Ljava/lang/Integer;", "onBindViewHolder", "", "item", "", "app_debug"})
    public static abstract class ViewHolder<B extends androidx.databinding.ViewDataBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final B binding = null;
        private final java.lang.Integer bindingVariableId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final B getBinding() {
            return null;
        }
        
        public final void onBindViewHolder(@org.jetbrains.annotations.Nullable()
        java.lang.Object item) {
        }
        
        public ViewHolder(@androidx.annotation.LayoutRes()
        int layoutRes, @org.jetbrains.annotations.Nullable()
        android.view.ViewGroup parent, @org.jetbrains.annotations.Nullable()
        java.lang.Integer bindingVariableId) {
            super(null);
        }
    }
}