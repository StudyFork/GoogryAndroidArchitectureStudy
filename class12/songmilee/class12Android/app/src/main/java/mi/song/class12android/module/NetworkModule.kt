package mi.song.class12android.module

import mi.song.class12android.data.source.remote.RetrofitHelper
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitHelper.getService(get()) }
}