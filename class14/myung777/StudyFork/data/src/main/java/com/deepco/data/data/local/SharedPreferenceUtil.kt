package com.deepco.data.data.local

import android.content.Context
import android.content.SharedPreferences
import com.deepco.data.data.model.RecentSearchData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val MOVIE: String = "movie"
private const val PREF_RECENT_SEARCH: String = "pref_recent_search"

class SharedPreferenceUtil @Inject constructor(
    applicationContext: Context
) {
    private val preference: SharedPreferences = applicationContext.getSharedPreferences(MOVIE, 0)
    private var gson = Gson()
    fun saveQuery(queryList: List<RecentSearchData>) {
        preference.edit().putString(PREF_RECENT_SEARCH, gson.toJson(queryList).toString()).apply()
    }

    fun getQueryList(): List<RecentSearchData> {
        val json = preference.getString(PREF_RECENT_SEARCH, null)
        var queryList = listOf<RecentSearchData>()
        val type = object : TypeToken<List<RecentSearchData?>?>() {}.type
        if (json != null) {
            queryList = gson.fromJson(json, type)
        }

        return queryList
    }

}

@Module
@InstallIn(ApplicationComponent::class)
class SharedPreferenceUtilModule {
    @Provides
    @Singleton
    fun provideSharedPreferenceUtil(@ApplicationContext applicationContext: Context): SharedPreferenceUtil {
        return SharedPreferenceUtil(applicationContext)
    }
}