package com.wybh.androidarchitecturestudy.model.local

import com.wybh.androidarchitecturestudy.App
import com.wybh.androidarchitecturestudy.SharedPref

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
        SharedPref.setSaveSearchWord(parseWord, App.instance)
    }

    override fun getSearchWord(): String? {
        return SharedPref.getSaveSearchWord(App.instance)
    }
}