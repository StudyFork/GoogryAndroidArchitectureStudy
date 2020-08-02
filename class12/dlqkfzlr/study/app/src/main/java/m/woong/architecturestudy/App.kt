package m.woong.architecturestudy

import android.app.Application
import android.content.Context

@Deprecated("Not Using Anymore")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null
        var appContext: Context? = null
            private set
    }
}