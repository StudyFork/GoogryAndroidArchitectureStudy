package io.github.sooakim.ui.base

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import kotlin.reflect.KClass

interface SANavigationProvider {
    fun <T : Activity> startActivity(targetClass: KClass<T>, bundle: Bundle? = null)

    fun startActivity(action: String, uri: Uri)

    fun finishActivity()
}