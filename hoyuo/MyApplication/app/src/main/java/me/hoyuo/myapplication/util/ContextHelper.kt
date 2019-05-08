package me.hoyuo.myapplication.util

import android.content.Context
import java.lang.ref.WeakReference

object ContextHelper {
    private var weakReference: WeakReference<Context>? = null

    var context: Context
        get() = if (weakReference?.get() != null) {
            weakReference!!.get()!!
        } else {
            throw IllegalStateException("Application context is null")
        }
        @Synchronized set(context) {
            weakReference = WeakReference(context)
        }

}