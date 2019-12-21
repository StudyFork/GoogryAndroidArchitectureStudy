package com.buddman1208.architecturestudy.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.buddman1208.architecturestudy.R
import com.buddman1208.architecturestudy.models.BookItem
import com.buddman1208.architecturestudy.models.CommonItem
import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.models.MovieItem
import com.buddman1208.architecturestudy.utils.Constants
import com.buddman1208.architecturestudy.utils.ErrorType
import com.buddman1208.architecturestudy.utils.removeHtmlBoldTags
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), BaseContract.View {

    private val datas: DataSource<Any> = dataSourceOf()
    private var currentMode: String by Delegates.observable(Constants.MODE_BLOG) { _, _, newValue ->
        updateToolbarTitle()
        search()
    }

    private val presenter: BasePresenter by lazy { BasePresenter(this) }

    private val loadingDialog : ProgressDialog by lazy {
        ProgressDialog(this@MainActivity).apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setMessage("Loading...")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initToolbar()
        initBottomNavigation()
        initView()
    }

    private fun initToolbar() {
        toolbar.apply {
            setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
            setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.colorPrimary
                )
            )
            contentInsetStartWithNavigation = 0
        }
        setSearchType(bottomTabView.selectedItemId)
    }

    private fun initBottomNavigation() {
        bottomTabView.setOnNavigationItemSelectedListener {
            setSearchType(it.itemId)
            true
        }
    }

    private fun initRecyclerView() {
        rvMain.setup {
            withDataSource(datas)
            withLayoutManager(LinearLayoutManager(this@MainActivity))
            withItem<CommonItem, CommonViewHolder>(R.layout.content_common_item) {
                onBind(::CommonViewHolder) { _, item ->
                    tvTitle.text = item.title.removeHtmlBoldTags()
                    tvDescription.text = item.description.removeHtmlBoldTags()
                }
                onClick {
                    browse(item.link)
                }
            }
            withItem<MovieItem, MovieViewHolder>(R.layout.content_movie_item) {
                onBind(::MovieViewHolder) { _, item ->
                    tvTitle.text = item.title.removeHtmlBoldTags()
                    tvSubtitleDate.text = resources.getString(
                        R.string.subtitle_date_format,
                        item.subtitle.removeHtmlBoldTags(),
                        item.pubDate.removeHtmlBoldTags()
                    )
                    tvDirectorActor.text = resources.getString(
                        R.string.director_actor_format,
                        item.director.removeHtmlBoldTags(),
                        item.actor.removeHtmlBoldTags()
                    )

                    Glide.with(this@MainActivity)
                        .load(item.image)
                        .into(ivThumbnail)
                }
                onClick {
                    browse(item.link)
                }
            }
            withItem<BookItem, BookViewHolder>(R.layout.content_book_item) {
                onBind(::BookViewHolder) { _, item ->
                    tvTitle.text = item.title.removeHtmlBoldTags()
                    tvAuthorPublisherDate.text =
                        resources.getString(
                            R.string.author_publisher_date_format,
                            item.author.removeHtmlBoldTags(),
                            item.publisher.removeHtmlBoldTags(),
                            item.pubdate.removeHtmlBoldTags()
                        )
                    tvDescription.text = item.description.removeHtmlBoldTags()

                    Glide.with(this@MainActivity)
                        .load(item.image)
                        .into(ivThumbnail)
                }
                onClick {
                    browse(item.link)
                }
            }

        }
    }

    private fun initView() {
        btnSearch.setOnClickListener { search() }
        etQuery.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> search()
                else -> {
                }
            }
            true
        }
    }

    override fun updateData(it: CommonResponse) {
        datas.clear()
        if (it.items.isEmpty()) {
            updateInfoText(resources.getString(R.string.list_blank))
        } else {
            updateInfoText("")
            when (currentMode) {
                Constants.MODE_BLOG, Constants.MODE_NEWS -> {
                    datas.addAll(
                        it.items.map {
                            Gson().fromJson(it, CommonItem::class.java)
                        }
                    )
                }
                Constants.MODE_MOVIE -> {
                    datas.addAll(
                        it.items.map {
                            Gson().fromJson(it, MovieItem::class.java)
                        }
                    )
                }
                Constants.MODE_BOOK -> {
                    datas.addAll(
                        it.items.map {
                            Gson().fromJson(it, BookItem::class.java)
                        }
                    )
                }
            }
        }
    }

    override fun showError(errorType: ErrorType) {
        datas.clear()
        updateInfoText(
            resources.getString(
                when (errorType) {
                    ErrorType.CONNECTION_ERROR -> R.string.connect_error
                    ErrorType.NO_QUERY -> R.string.hint_input_edittext
                    ErrorType.NO_RESULT-> R.string.list_blank
                }
            )
        )
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    private fun setSearchType(@IdRes itemId: Int) {
        currentMode = when (itemId) {
            R.id.menuBlog -> Constants.MODE_BLOG
            R.id.menuBook -> Constants.MODE_BOOK
            R.id.menuMovie -> Constants.MODE_MOVIE
            R.id.menuNews -> Constants.MODE_NEWS
            else -> ""
        }
    }

    private fun updateToolbarTitle() {
        supportActionBar?.title = when (currentMode) {
            Constants.MODE_BLOG -> "블로그"
            Constants.MODE_BOOK -> "도서"
            Constants.MODE_MOVIE -> "영화"
            Constants.MODE_NEWS -> "뉴스"
            else -> ""
        }
    }

    private fun updateInfoText(info: String) = tvInfo.apply {
        text = info
        visibility = if (info.isNotBlank()) {
            View.VISIBLE
        } else View.INVISIBLE
    }

    private fun getQuery() = etQuery.text.toString().trim()

    private fun search() = presenter.searchByQuery(getQuery(), currentMode)
}

class CommonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvSubtitleDate: TextView = view.findViewById(R.id.tvSubtitleDate)
    val tvDirectorActor: TextView = view.findViewById(R.id.tvDirectorActor)
}

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvAuthorPublisherDate: TextView = view.findViewById(R.id.tvAuthorPublisherDate)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
}