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
import com.example.kotlinapplication.util.BaseFragment
import com.example.kotlinapplication.data.BlogItem
import com.example.kotlinapplication.data.ImageItem
import com.example.kotlinapplication.data.KinItem
import com.example.kotlinapplication.data.MovieItem
import kotlinx.android.synthetic.main.fragment_page.*


class PageFragment : BaseFragment(), ListMovieAdapter.ItemListener, ListImageAdapter.ItemListener,
    ListBlogAdapter.ItemListener, ListKinAdapter.ItemListener, Contract.View {

    private lateinit var movieAdapter: ListMovieAdapter
    private lateinit var blogAdapter: ListBlogAdapter
    private lateinit var imageAdapter: ListImageAdapter
    private lateinit var kinAdapter: ListKinAdapter

    private var type: Int? = null
    private lateinit var presenter: Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = Presenter(this)
        type = arguments?.getInt(EXTRA_MESSAGE)
        home_search_btn.text ="$type 검색"
        when (type) {
            MOVIE_VIEW-> {
                movieAdapter = ListMovieAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = movieAdapter
                }
            }
            IMAGE_VIEW-> {
                imageAdapter = ListImageAdapter(this)
                with(home_recyclerview) {
                    layoutManager = GridLayoutManager(activity, 4)
                    adapter = imageAdapter
                }
            }
            BLOG_VIEW-> {
                blogAdapter = ListBlogAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = blogAdapter
                }
            }
            KIN_VIEW-> {
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
            if (home_search_edit.text.isBlank()) {
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

    companion object {

        val MOVIE_VIEW = 0
        val IMAGE_VIEW = 1
        val BLOG_VIEW = 2
        val KIN_VIEW = 3

        fun newInstance(message: Int): PageFragment {
            val pageFragment = PageFragment()
            val bundle = Bundle(1)
            bundle.putInt(EXTRA_MESSAGE, message)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }
}
