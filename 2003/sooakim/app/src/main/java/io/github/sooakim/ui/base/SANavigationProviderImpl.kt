package io.github.sooakim.ui.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class SANavigationProviderImpl(
    activity: Activity
) : SANavigationProvider {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)

    override fun <T : Activity> startActivity(targetClass: KClass<T>, bundle: Bundle?) {
        activityRef.get()?.run {
            startActivity(Intent(this, targetClass.java).apply {
                if (bundle != null) putExtras(bundle)
            })
        }
    }

    override fun startActivity(action: String, uri: Uri) {
        activityRef.get()?.run {
            Intent(action, uri).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
    }

    override fun finishActivity() {
        activityRef.get()?.finish()
    }
}