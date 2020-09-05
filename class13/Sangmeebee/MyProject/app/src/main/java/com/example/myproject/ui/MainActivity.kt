package com.example.myproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.MovieAdapter
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.data.repository.NaverRepository
import com.example.myproject.data.repository.NaverRepositoryImpl
import com.example.myproject.data.source.local.NaverLocalDataSourceImpl
import com.example.myproject.data.source.remote.NaverRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var movies: ArrayList<Items> = ArrayList()
    private val movieAdapter = MovieAdapter(this, movies)

    private val repositoryDataSourceImpl: NaverRepository by lazy {
        val naverRemoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val naverLocalDataSourceImpl = NaverLocalDataSourceImpl()
        NaverRepositoryImpl(naverLocalDataSourceImpl, naverRemoteDataSourceImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        btn_search.setOnClickListener {
            val title = edit_title.text.toString()
            if (title.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                recyclerview.layoutManager?.scrollToPosition(0)
                repositoryDataSourceImpl.getMovieList(title, {
                    if (it.isEmpty()) {
                        movieAdapter.clearItems()
                        Toast.makeText(
                            applicationContext,
                            "$title 를 찾을 수 없습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        movieAdapter.clearAndAddItems(it)
                    }

                }, {
                    Toast.makeText(this, "error : $it", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun setRecyclerView() {
        recyclerview.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }
}
