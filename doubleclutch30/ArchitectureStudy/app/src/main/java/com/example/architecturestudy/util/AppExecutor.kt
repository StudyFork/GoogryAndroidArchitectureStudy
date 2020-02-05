package com.example.architecturestudy.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

open class AppExecutor constructor(
    val mainThread: Executor = MainThreadExecutor()
) {
    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }
}