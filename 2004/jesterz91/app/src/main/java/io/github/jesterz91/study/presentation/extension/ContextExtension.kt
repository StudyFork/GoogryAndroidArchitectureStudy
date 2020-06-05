package io.github.jesterz91.study.presentation.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()