package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class SharedPreferencesTool @Inject constructor(
    private val appCtx: Application
) : DBTool {

    override fun putData(prefFileKey: String, putKey: String, data: String) {
        appCtx.getSharedPreferences(
            prefFileKey,
            Context.MODE_PRIVATE
        ).edit().putString(
            putKey, data
        ).commit()
    }

    override fun getData(prefFileKey: String, getKey: String): String {
        return appCtx.getSharedPreferences(
            prefFileKey,
            Context.MODE_PRIVATE
        ).getString(getKey, null)
            ?: ""
    }

}

@InstallIn(ApplicationComponent::class)
@Module
abstract class SharedPreferencesToolModule {

    @NosqlTool
    @Binds
    @Singleton
    abstract fun bindSharedPreferencesTool(sharedPreferencesTool: SharedPreferencesTool): DBTool

}