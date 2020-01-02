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
                if (it.blogs.isEmpty()) {
                    updateBlogSearchHistory(
                        it.blogs,
                        fun1 = { repository.clearBlogResult() },
                        fun2 = { repository.saveBlogKeyword(keyword) }
                    )
                } else {
                    val blogList = ensureBlogEntityList(it.blogs)
                    updateBlogSearchHistory(
                        it.blogs,
                        fun1 = { repository.clearBlogResult() },
                        fun2 = { repository.saveBlogKeyword(keyword) },
                        fun3 = { repository.saveBlogResult(blogList) })
                }
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

    private fun ensureBlogEntityList(blogs: List<Blog>): List<BlogEntity> =
        arrayListOf<BlogEntity>().apply {
            blogs.mapTo(this) { blog ->
                BlogEntity(
                    bloggerLink = blog.bloggerLink,
                    bloggerName = blog.bloggerName,
                    description = blog.description,
                    link = blog.link,
                    postdate = blog.postdate,
                    title = blog.title
                )
            }
        }

    private fun updateBlogSearchHistory(
        blogs: List<Blog>,
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Single<List<Blog>> {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            firstCall
                .andThen(secondCall)
                .andThen(thirdCall)
                .toSingle { blogs }
        } ?: run {
            firstCall
                .andThen(secondCall)
                .toSingle { blogs }
        }
    }

}