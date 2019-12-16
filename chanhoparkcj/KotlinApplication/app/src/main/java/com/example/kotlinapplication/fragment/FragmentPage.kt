package com.example.kotlinapplication.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.ListBlogAdapter
import com.example.kotlinapplication.adapter.ListImageAdapter
import com.example.kotlinapplication.adapter.ListKinAdapter
import com.example.kotlinapplication.adapter.ListMovieAdapter
import com.example.kotlinapplication.model.BlogItems
import com.example.kotlinapplication.model.ImageItems
import com.example.kotlinapplication.model.KinItems
import com.example.kotlinapplication.model.MovieItems
import com.example.kotlinapplication.model.repository.DataRepositoryImpl
import kotlinx.android.synthetic.main.fragment_page.*


class FragmentPage : Fragment(), ListMovieAdapter.ItemListener, ListImageAdapter.ItemListener,
    ListBlogAdapter.ItemListener, ListKinAdapter.ItemListener {
    private var movieAdapter: ListMovieAdapter? = null
    private var blogAdapter: ListBlogAdapter? = null
    private var imageAdapter: ListImageAdapter? = null
    private var kinAdapter: ListKinAdapter? = null
    private var type: String? = null
    private lateinit var dataRepository: DataRepositoryImpl

    companion object {
        fun newInstance(message: String): FragmentPage {
            val f = FragmentPage()
            val bdl = Bundle(1)
            bdl.putString(EXTRA_MESSAGE, message)
            f.arguments = bdl
            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
        viewModelListener()
    }

    private fun start() {
        dataRepository = DataRepositoryImpl()
        type = arguments?.getString(EXTRA_MESSAGE)
        home_search_btn.text = type + " 검색"
        imageAdapter = ListImageAdapter(this)
        movieAdapter = ListMovieAdapter(this)
        blogAdapter = ListBlogAdapter(this)
        kinAdapter = ListKinAdapter(this)
    }


    private fun setUpBuuttonClickListener() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.isEmpty()) {
                Toast.makeText(context, "검색어를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "검색어 :" + home_search_edit.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                loadMovieData(type, home_search_edit.text.toString())
            }
        }

    }

    private fun loadMovieData(type: String?, query: String) {
        when (type) {
            "영화" -> dataRepository.remote.getMovieCall(query)
            "이미지" -> dataRepository.remote.getImageCall(query)
            "블로그" -> dataRepository.remote.getBlogCall(query)
            "지식인" -> dataRepository.remote.getKinCall(query)
            else -> {
                Log.d("Error", "error")
            }
        }

    }

    private fun viewModelListener() {
        dataRepository.remote.movieList.observe(this, Observer {
            movieAdapter?.addAllItems(it)
            with(home_recyclerview) {
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = movieAdapter
            }
        })
        dataRepository.remote.imageList.observe(this, Observer {
            imageAdapter?.addAllItems(it)
            with(home_recyclerview) {
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = imageAdapter
            }
        })
        dataRepository.remote.blogList.observe(this, Observer {
            blogAdapter?.addAllItems(it)
            with(home_recyclerview) {
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = blogAdapter
            }
        })
        dataRepository.remote.kinList.observe(this, Observer {
            kinAdapter?.addAllItems(it)
            with(home_recyclerview) {
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                adapter = kinAdapter
            }
        })
    }

    override fun onMovieItemClick(movieItems: MovieItems) {
        Toast.makeText(activity, movieItems.link, Toast.LENGTH_SHORT).show()
        val uri: Uri = Uri.parse(movieItems.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }

    override fun onImageItemClick(imageItems: ImageItems) {
        Toast.makeText(activity, imageItems.link, Toast.LENGTH_SHORT).show()
        val uri: Uri = Uri.parse(imageItems.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onBlogItemClick(blogItems: BlogItems) {
        Toast.makeText(activity, blogItems.link, Toast.LENGTH_SHORT).show()
        val uri: Uri = Uri.parse(blogItems.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onKinItemClick(kinItems: KinItems) {
        Toast.makeText(activity, kinItems.link, Toast.LENGTH_SHORT).show()
        val uri: Uri = Uri.parse(kinItems.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
