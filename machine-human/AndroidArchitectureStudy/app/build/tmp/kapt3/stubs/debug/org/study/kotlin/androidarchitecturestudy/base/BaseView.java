package org.study.kotlin.androidarchitecturestudy.base;

import java.lang.System;

/**
 * ***************************
 * BaseView - structure
 *
 * i = interface
 * f = function
 * v = variable
 * ***************************
 *
 * i = BaseView<T>
 *
 *    v = presenter: T
 *    f = showProgress(text: String)
 *    f = hideProgress()
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/study/kotlin/androidarchitecturestudy/base/BaseView;", "", "showErrorToast", "", "errorMessage", "", "app_debug"})
public abstract interface BaseView {
    
    public abstract void showErrorToast(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable errorMessage);
}