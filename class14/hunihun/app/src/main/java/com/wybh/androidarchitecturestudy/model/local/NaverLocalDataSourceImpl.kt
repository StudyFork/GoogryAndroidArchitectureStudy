package com.wybh.androidarchitecturestudy.model.local

import android.util.Log
import com.wybh.androidarchitecturestudy.util.App
import com.wybh.androidarchitecturestudy.util.SharedPref

class NaverLocalDataSourceImpl : NaverLocalDataSource {
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
        SharedPref.setSaveSearchWord(parseWord, App.instance)
    }

    override fun getSearchWord(): String? {
        Log.e("jsh","getSaveSearchWord >>> " + SharedPref.getSaveSearchWord(App.instance))
        return SharedPref.getSaveSearchWord(App.instance)
    }
}