package com.buddman1208.architecturestudy

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
import com.buddman1208.architecturestudy.models.BookItem
import com.buddman1208.architecturestudy.models.CommonItem
import com.buddman1208.architecturestudy.models.MovieItem
import com.buddman1208.architecturestudy.utils.Constants
import com.buddman1208.architecturestudy.utils.NetworkManager
import com.buddman1208.architecturestudy.utils.onUI
import com.buddman1208.architecturestudy.utils.subscribeOnIO
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val datas: DataSource<Any> = dataSourceOf()
    var currentMode: String = Constants.MODE_BLOG

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
            setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
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
                onBind(::CommonViewHolder) { index, item ->
                    tvTitle.text = item.title
                    tvDescription.text = item.description
                }
                onClick {
                    browse(item.link)
                }
            }
            withItem<MovieItem, MovieViewHolder>(R.layout.content_movie_item) {
                onBind(::MovieViewHolder) { index, item ->
                    tvTitle.text = item.title
                    tvSubtitleDate.text = "${item.subtitle}, ${item.pubDate}"
                    tvDirectorActor.text = "감독 ${item.director}, 배우 ${item.actor}"

                    Glide.with(this@MainActivity)
                        .load(item.image)
                        .into(ivThumbnail)
                }
                onClick {
                    browse(item.link)
                }
            }
            withItem<BookItem, BookViewHolder>(R.layout.content_book_item) {
                onBind(::BookViewHolder) { index, item ->
                    tvTitle.text = item.title
                    tvAuthorPublisherDate.text = "${item.author}, ${item.publisher}, ${item.pubdate}"
                    tvDescription.text = item.description

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
        btnSearch.setOnClickListener { getData() }
        etQuery.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> getData()
                else -> {
                }
            }
            true
        }
    }

    private fun setSearchType(@IdRes itemId: Int) {
        currentMode = when (itemId) {
            R.id.menuBlog -> Constants.MODE_BLOG
            R.id.menuBook -> Constants.MODE_BOOK
            R.id.menuMovie -> Constants.MODE_MOVIE
            R.id.menuNews -> Constants.MODE_NEWS
            else -> ""
        }
        updateToolbarTitle()
        getData()
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
        visibility = if (info.isNotBlank()) View.VISIBLE else View.INVISIBLE
    }

    private fun getData() {
        val query = etQuery.text.toString().trim()
        if (query.isNotBlank()) {
            NetworkManager
                .searchByType(searchType = currentMode, query = query)
                .subscribeOnIO()
                .onUI {
                    when (it.code()) {
                        200 -> {
                            datas.clear()
                            if (it.body()!!.items.isEmpty())
                                updateInfoText(resources.getString(R.string.list_blank))
                            else
                                when (currentMode) {
                                    Constants.MODE_BLOG, Constants.MODE_NEWS -> {
                                        datas.addAll(
                                            it.body()!!.items.map { Gson().fromJson(it, CommonItem::class.java) }
                                        )
                                    }
                                    Constants.MODE_MOVIE -> {
                                        datas.addAll(
                                            it.body()!!.items.map { Gson().fromJson(it, MovieItem::class.java) }
                                        )
                                    }
                                    Constants.MODE_BOOK -> {
                                        datas.addAll(
                                            it.body()!!.items.map { Gson().fromJson(it, BookItem::class.java) }
                                        )
                                    }
                                }
                        }
                        else -> toast(resources.getString(R.string.connect_error))
                    }
                }
        } else {
            datas.clear()
            updateInfoText(resources.getString(R.string.hint_input_edittext))
        }
    }
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