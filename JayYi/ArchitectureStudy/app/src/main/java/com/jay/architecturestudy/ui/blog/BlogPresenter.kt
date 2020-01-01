package com.jay.architecturestudy.ui.blog

import android.util.Log
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        val lastKeyword = repository.getLatestBlogKeyword()
        loadBlogSearchHistory(
            keyword = lastKeyword
        )
            .subscribe({
                view.updateUi(it.first, it.second)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("blog", message)
            })
    }

    private fun loadBlogSearchHistory(keyword: String) : Single<Pair<String, List<Blog>>> {
        return if (keyword.isBlank()) {
            Single.just(Pair(keyword, emptyList()))
        } else {
            repository.getLatestBlogResult()
                .map { Pair(keyword, it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                clearSearchHistory { repository.clearBlogResult() }
                it.blogs.isNotEmpty().then {
                    val blogList = arrayListOf<BlogEntity>()
                    it.blogs.mapTo(blogList) { blog ->
                        BlogEntity(
                            bloggerLink = blog.bloggerLink,
                            bloggerName = blog.bloggerName,
                            description = blog.description,
                            link = blog.link,
                            postdate = blog.postdate,
                            title = blog.title
                        )
                    }
                    // 최신 결과 저장
                    updateSearchHistory { repository.saveBlogResult(blogList) }
                }
                repository.saveBlogKeyword(keyword)
                it.blogs
            }
            .compose(singleIoMainThread())
            .subscribe({ blogs ->
                if (blogs.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(blogs)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}