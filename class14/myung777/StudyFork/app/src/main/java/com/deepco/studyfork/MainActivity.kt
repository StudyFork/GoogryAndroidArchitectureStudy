package com.deepco.studyfork

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepco.studyfork.api.RetrofitService
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieData
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var api: RetrofitService
    private lateinit var recyclerAdapterMovie: RecyclerAdapterMovie
    private val repositoryDataSourceImpl: RepositoryMovieData by lazy {
        val remoteMovieDataImpl = RemoteMovieDataImpl(api)
        RepositoryMovieDataImpl(remoteMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = RetrofitService.create()
        setRecyclerView()
        search_btn.setOnClickListener {
            val text = movie_edit_text.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "영화 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                repositoryDataSourceImpl.getMovieList(text, {
                    if (it.isEmpty()) {
                        recyclerAdapterMovie.clear()
                        Toast.makeText(this, "$text 를 찾을 수 없습니다", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        recyclerAdapterMovie.setItemList(it)
                    }
                }, {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun setRecyclerView() {
        recyclerAdapterMovie = RecyclerAdapterMovie()
        recycler_view.adapter = recyclerAdapterMovie
    }
}