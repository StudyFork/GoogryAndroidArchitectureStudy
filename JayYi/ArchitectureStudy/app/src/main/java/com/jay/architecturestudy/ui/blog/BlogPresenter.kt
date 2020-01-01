package com.jay.architecturestudy.ui.blog

import android.util.Log
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        loadData()
    }

    private fun loadData() {
        val lastKeyword = repository.getLatestBlogKeyword()
        if (lastKeyword.isBlank()) {
            view.updateUi(lastKeyword, emptyList())
        } else {
            repository.getLatestBlogResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("blog", message)
                })
                .addTo(disposables)
        }
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                updateBlogResult { repository.clearBlogResult() }
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
                    updateBlogResult {
                        // 최신 결과 저장
                        repository.saveBlogResult(blogList)
                    }
                }
                repository.saveBlogKeyword(keyword)
                it.blogs
            }
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

    private fun updateBlogResult(func: () -> Unit) {
        Completable.fromCallable(func)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}