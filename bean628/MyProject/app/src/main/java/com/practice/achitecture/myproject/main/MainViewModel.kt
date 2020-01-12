package com.practice.achitecture.myproject.main

import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchViewModel
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.ext.default
import com.practice.achitecture.myproject.model.SearchedItem

class MainViewModel constructor(private val naverRepository: NaverRepository) :
    BaseNaverSearchViewModel() {

    val query = MutableLiveData<String>().default("")
    val eventQueryEmpty = MutableLiveData<Boolean>().default(false)
    val eventGoToHistoryActivity = MutableLiveData<Boolean>().default(false)

    fun search(searchType: SearchType) {
        lastSearchType.value = searchType
        search()
    }

    fun search() {
        if (TextUtils.isEmpty(query.value)) {
            eventQueryEmpty.value = true
        } else {
            eventHideSoftKeyboard.value = true
            eventProgressBarIsShowing.value = true
            if (lastSearchType.value != null && query.value != null) {
                naverRepository.searchWordByNaver(
                    lastSearchType.value!!,
                    query.value!!,
                    object : NaverDataSource.GettingResultOfSearchingCallback {
                        override fun onSuccess(items: List<SearchedItem>) {
                            eventProgressBarIsShowing.value = false
                            this@MainViewModel.movieOrBookItems.value = items
                        }

                        override fun onFailure(throwable: Throwable) {
                            eventProgressBarIsShowing.value = false
                            eventStringMessageId.value = R.string.toast_network_error_msg
                        }
                    })
            } else {
                eventProgressBarIsShowing.value = false
            }
        }

    }

    fun goToHistoryActivity() {
        eventGoToHistoryActivity.value = true
    }

    fun onEditorAction(tv: TextView, actionId: Int, event: KeyEvent?): Boolean {
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                search()
                return true
            }
        }
        return false
    }

    fun loadCache() {
        val lastSearchType = naverRepository.getLastSearchType()
        val jsonParser = JsonParser()
        val jsonObject = jsonParser.parse(naverRepository.getCache(lastSearchType)) as JsonObject

        if (jsonObject.has("word") && jsonObject.has("list")) {
            val gson = Gson()
            val searchedItemListType = object : TypeToken<List<SearchedItem>>() {}.type
            query.value = jsonObject.get("word").asString.replace("\"", "")
            movieOrBookItems.value =
                gson.fromJson(jsonObject.get("list").asString, searchedItemListType)
        }
    }

}