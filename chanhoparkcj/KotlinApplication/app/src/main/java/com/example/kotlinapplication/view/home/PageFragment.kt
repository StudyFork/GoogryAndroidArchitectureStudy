package com.example.kotlinapplication.view.home

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListBlogAdapter
import com.example.kotlinapplication.adapter.ListImageAdapter
import com.example.kotlinapplication.adapter.ListKinAdapter
import com.example.kotlinapplication.adapter.ListMovieAdapter
import com.example.kotlinapplication.view.home.presenter.Contract
import com.example.kotlinapplication.view.home.presenter.Presenter
import com.example.kotlinapplication.util.baseFragment
import com.example.kotlinapplication.data.BlogItem
import com.example.kotlinapplication.data.ImageItem
import com.example.kotlinapplication.data.KinItem
import com.example.kotlinapplication.data.MovieItem
import kotlinx.android.synthetic.main.fragment_page.*


class PageFragment : baseFragment(), ListMovieAdapter.ItemListener, ListImageAdapter.ItemListener,
    ListBlogAdapter.ItemListener, ListKinAdapter.ItemListener, Contract.View {

    private lateinit var movieAdapter: ListMovieAdapter
    private lateinit var blogAdapter: ListBlogAdapter
    private lateinit var imageAdapter: ListImageAdapter
    private lateinit var kinAdapter: ListKinAdapter

    private var type: String? = null
    private lateinit var presenter: Presenter

    companion object {

        fun newInstance(message: String): PageFragment {
            val f = PageFragment()
            val bdl = Bundle(1)
            bdl.putString(EXTRA_MESSAGE, message)
            f.arguments = bdl
            return f
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }


    private fun start() {
        presenter = Presenter(this)
        type = arguments?.getString(EXTRA_MESSAGE)
        home_search_btn.text ="$type 검색"
        when (type) {
            "영화" -> {
                movieAdapter = ListMovieAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = movieAdapter
                }
            }
            "이미지" -> {
                imageAdapter = ListImageAdapter(this)
                with(home_recyclerview) {
                    layoutManager = GridLayoutManager(activity, 4)
                    adapter = imageAdapter
                }
            }
            "블로그" -> {
                blogAdapter = ListBlogAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = blogAdapter
                }
            }
            "지식인" -> {
                kinAdapter = ListKinAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = kinAdapter
                }
            }
        }
    }

    private fun setUpBuuttonClickListener() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.trim().isEmpty()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${home_search_edit.text}")
                presenter.loadData(type, home_search_edit.text.toString())
            }
        }

    }

    override fun onMovieItemClick(movieItems: MovieItem) {
        toast(movieItems.link)
        webLink(movieItems.link)
    }

    override fun onImageItemClick(imageItems: ImageItem) {
        toast(imageItems.link)
        webLink(imageItems.link)
    }

    override fun onBlogItemClick(blogItems: BlogItem) {
        toast(blogItems.link)
        webLink(blogItems.link)
    }

    override fun onKinItemClick(kinItems: KinItem) {
        toast(kinItems.link)
        webLink(kinItems.link)
    }

    override fun getMovie(movieItems: List<MovieItem>) {
        movieAdapter.addAllItems(movieItems)
    }

    override fun getImage(imageItems: List<ImageItem>) {
        imageAdapter.addAllItems(imageItems)
    }

    override fun getBlog(blogItems: List<BlogItem>) {
        blogAdapter.addAllItems(blogItems)
    }

    override fun getKin(kinItems: List<KinItem>) {
        kinAdapter.addAllItems(kinItems)
    }

    override fun getError(message: String) {
        toast(message)
    }
}
