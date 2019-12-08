package com.example.kotlinapplication.fragment

import android.content.ContentValues.TAG
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.ListBlogAdapter
import com.example.kotlinapplication.adapter.ListImageAdapter
import com.example.kotlinapplication.adapter.ListKinAdapter
import com.example.kotlinapplication.adapter.ListMovieAdapter
import com.example.kotlinapplication.model.*
import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.fragment_page.*


class FragmentPage : Fragment(), ListMovieAdapter.ItemListener, ListImageAdapter.ItemListener,
    ListBlogAdapter.ItemListener, ListKinAdapter.ItemListener {
    private var service: RetrofitService? = null
    private var movieAdapter: ListMovieAdapter? = null
    private var blogAdapter: ListBlogAdapter? = null
    private var imageAdapter: ListImageAdapter? = null
    private var kinAdapter: ListKinAdapter? = null

    private var type: String? = null

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
    }

    private fun start() {
        service = RetrofitClient.client!!.create(RetrofitService::class.java)
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
            "영화" -> service!!.getMovieCall(query)
                .observeOn(mainThread())
                .subscribeOn(io())
                .subscribe(this::handleResponseMovie, this::handleError)
            "이미지" -> service!!.getImageCall(query)
                .observeOn(mainThread())
                .subscribeOn(io())
                .subscribe(this::handleResponseImage, this::handleError)
            "블로그" -> service!!.getBlogCall(query)
                .observeOn(mainThread())
                .subscribeOn(io())
                .subscribe(this::handleResponseBlog, this::handleError)
            "지식인" -> service!!.getKinCall(query)
                .observeOn(mainThread())
                .subscribeOn(io())
                .subscribe(this::handleResponseKin, this::handleError)
            else -> {
                Log.d("Error", "error")
            }
        }

    }

    private fun handleResponseMovie(res: ResponseItems<MovieItems>) {
        movieAdapter?.addAllItems(res.items)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(
                activity, RecyclerView.VERTICAL, false
            )
            adapter = movieAdapter
        }

    }

    private fun handleResponseImage(res: ResponseItems<ImageItems>) {
        imageAdapter?.addAllItems(res.items)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(
                activity, RecyclerView.VERTICAL, false
            )
            adapter = imageAdapter
        }
    }

    private fun handleResponseBlog(res: ResponseItems<BlogItems>) {
        blogAdapter?.addAllItems(res.items)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(
                activity, RecyclerView.VERTICAL, false
            )
            adapter = blogAdapter
        }
    }

    private fun handleResponseKin(res: ResponseItems<KinItems>) {
        kinAdapter?.addAllItems(res.items)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(
                activity, RecyclerView.VERTICAL, false
            )
            adapter = kinAdapter
        }
    }


    private fun handleError(error: Throwable) {
        Log.d(TAG, "Error ${error.localizedMessage}")
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
