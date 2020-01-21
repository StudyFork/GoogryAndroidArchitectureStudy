package com.god.taeiim.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverRepository

class ContentsViewModel(
    private val searchType: Tabs,
    private val naverRepository: NaverRepository
) : ViewModel() {

    private val _searchResultList = MutableLiveData<List<SearchResultShow.Item>>()
    private val _errorQueryBlank = MutableLiveData<Throwable>()
    private val _errorFailSearch = MutableLiveData<Throwable>()
    val searchResultList get() = _searchResultList
    val errorQueryBlank get() = _errorQueryBlank
    val errorFailSearch get() = _errorFailSearch
    val query = MutableLiveData<String>()

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        query.value = s.toString()
    }

    fun searchContents() {
        if (getQueryStr().isBlank()) {
            errorQueryBlank.value = Throwable()

        } else {
            naverRepository.getResultData(
                searchType,
                getQueryStr(),
                success = {
                    searchResultList.value = searchResultShowWrapper(searchType, it).items
                    naverRepository.saveSearchResult(
                        SearchHistory(it.items, searchType.name, getQueryStr())
                    )
                },
                fail = {
                    errorFailSearch.value = it
                }
            )
        }
    }

    private fun getQueryStr(): String {
        return query.value.toString()
    }

    fun getLastSearchHistory() {
        naverRepository.getLastSearchResultData(searchType)
            ?.let {
                searchResultList.value = searchResultShowWrapper(
                    searchType,
                    SearchResult(it.resultList)
                ).items
                query.value = it.query
            }
    }

    private fun searchResultShowWrapper(
        searchType: Tabs,
        searchResult: SearchResult
    ): SearchResultShow {
        val searchResultShow = SearchResultShow(searchResult.items.map {
            SearchResultShow.Item(it.title, it.subtitle, it.description, it.link, it.image)
        })

        searchResult.items.map { item: SearchResult.Item ->
            searchResultShow.items.map {
                when (searchType) {
                    Tabs.BLOG -> it.subtitle = item.postdate
                    Tabs.NEWS -> it.subtitle = item.pubDate
                    Tabs.MOVIE -> {
                        it.subtitle = item.pubDate
                        it.description = (item.director + item.actor)
                    }
                    Tabs.BOOK -> it.subtitle = item.author
                }
            }
        }

        return searchResultShow
    }
}