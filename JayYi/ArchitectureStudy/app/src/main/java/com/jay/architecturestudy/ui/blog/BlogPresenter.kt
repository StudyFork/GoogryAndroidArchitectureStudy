package com.jay.architecturestudy.ui.blog

import android.util.Log
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestBlogKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestBlogResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("blog", message)
                })
        }
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .flatMap {
                repository.refreshBlogSearchHistory(
                    keyword = keyword,
                    blogs = it.blogs
                )
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