package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverRepository

class ContentsPresenter(
    private val naverRepository: NaverRepository,
    private val view: ContentsContract.View
) : ContentsContract.Presenter {

    override fun start() {

    }

    override fun searchContents(searchType: Tabs, query: String) {
        if (query.isBlank()) {
            view.blankSearchQuery()

        } else {
            naverRepository.getResultData(
                searchType,
                query,
                success = {
                    view.updateItems(searchResultShowWrapper(searchType, it).items)
                    naverRepository.saveSearchResult(SearchHistory(it.items, searchType.name, query))
                },
                fail = { view.failToSearch() }
            )
        }
    }

    override fun getLastSearchHistory(searchType: Tabs) {
        naverRepository.getLastSearchResultData(searchType)
            ?.let {
                view.updateItems(
                    searchResultShowWrapper(
                        searchType,
                        SearchResult(it.resultList)
                    ).items
                )
            }
    }

    override fun searchResultShowWrapper(
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
                    else -> it
                }
            }
        }

        return searchResultShow
    }
}