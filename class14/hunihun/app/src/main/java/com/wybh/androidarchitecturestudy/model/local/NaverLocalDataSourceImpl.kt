package com.wybh.androidarchitecturestudy.model.local

import android.util.Log
import com.wybh.androidarchitecturestudy.util.App
import com.wybh.androidarchitecturestudy.util.SharedPref
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPref
) : NaverLocalDataSource {
    override fun saveSearchWord(word: String) {
        var parseWord = if (getSearchWord().isNullOrEmpty()) word else "$word," + getSearchWord()
        val parseWordList = parseWord.split(",")
        if (parseWordList.size > 5) {
            parseWord = ""
            parseWordList.forEachIndexed { index, s ->
                if (index > 4) return@forEachIndexed
                parseWord = if (parseWord.isEmpty()) s else "$parseWord,$s"
            }
        }
        Log.e("jsh","parseWord >>> $parseWord")
        sharedPref.setSaveSearchWord(parseWord, App.instance)
    }

    override fun getSearchWord(): String? {
        Log.e("jsh","getSaveSearchWord >>> " + sharedPref.getSaveSearchWord(App.instance))
        return sharedPref.getSaveSearchWord(App.instance)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNaverLocalDataSource(localMovieDataImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}