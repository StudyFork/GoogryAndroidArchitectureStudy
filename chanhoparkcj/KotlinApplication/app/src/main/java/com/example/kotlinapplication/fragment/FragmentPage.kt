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
import androidx.recyclerview.widget.GridLayoutManager
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
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_page.*


class FragmentPage : Fragment(), ListMovieAdapter.ItemListener, ListImageAdapter.ItemListener,
    ListBlogAdapter.ItemListener, ListKinAdapter.ItemListener {
    private lateinit var movieAdapter: ListMovieAdapter
    private lateinit var blogAdapter: ListBlogAdapter
    private lateinit var imageAdapter: ListImageAdapter
    private lateinit var kinAdapter: ListKinAdapter
    private lateinit var dataRepositoryImpl: DataRepositoryImpl
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
        dataRepositoryImpl = DataRepositoryImpl()
        type = arguments?.getString(EXTRA_MESSAGE)
        home_search_btn.text = type + " 검색"
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
            "영화" -> dataRepositoryImpl.callMovieResources(query).subscribe(Consumer {
                movieAdapter?.addAllItems(it.items)
            })
            "이미지" -> dataRepositoryImpl.callImageResources(query).subscribe(Consumer {
                imageAdapter?.addAllItems(it.items)
            })
            "블로그" -> dataRepositoryImpl.callBlogResources(query).subscribe(Consumer {
                blogAdapter?.addAllItems(it.items)
            })
            "지식인" -> dataRepositoryImpl.callKinResources(query).subscribe(Consumer {
                kinAdapter?.addAllItems(it.items)
            })
            else -> {
                Log.d("Error", "error")
            }
        }
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
