package com.god.taeiim.myapplication.ui

import androidx.databinding.ObservableField
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverRepository

class ContentsViewModel(
    private val naverRepository: NaverRepository
) {
    val searchResultList = ObservableField<List<SearchResultShow.Item>>()
    var query = ""
    var searchType = Tabs.BLOG

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        query = s.toString()
    }

    fun searchContents() {
        if (getQueryStr().isBlank()) {
            searchResultList.set(null)

        } else {
            naverRepository.getResultData(
                searchType,
                getQueryStr(),
                success = {
                    searchResultList.set(searchResultShowWrapper(searchType, it).items)
                    naverRepository.saveSearchResult(
                        SearchHistory(it.items, searchType.name, getQueryStr())
                    )
                },
                fail = {
                    searchResultList.set(null)
                }
            )
        }
    }

    fun getQueryStr(): String {
        return query
    }

    fun getLastSearchHistory(searchType: Tabs) {
        naverRepository.getLastSearchResultData(searchType)
            ?.let {
                searchResultList.set(
                    searchResultShowWrapper(
                        searchType,
                        SearchResult(it.resultList)
                    ).items
                )
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