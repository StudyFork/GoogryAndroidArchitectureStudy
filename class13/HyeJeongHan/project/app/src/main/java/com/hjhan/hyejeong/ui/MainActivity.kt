package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl
import com.hjhan.hyejeong.ui.QueryHistoryDialog.Companion.HISTORY_DIALOG_TAG

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var historyButton: Button

    private lateinit var movieAdapter: MovieAdapter

    private val repositoryImpl: NaverRepositoryImpl by lazy {
        val remoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val localDataSourceImpl = NaverLocalDataSourceImpl()
        NaverRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        historyButton = findViewById(R.id.history_button)
        recyclerView = findViewById(R.id.movie_recycler_view)

        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter


        searchButton.setOnClickListener {

            val title = editText.text.toString()

            if (title.isBlank()) {
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()

            } else {
                requestMovieList(title)
            }
        }

        historyButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("list", ArrayList(repositoryImpl.getQueryList()))
            QueryHistoryDialog().apply {
                arguments = bundle
                show(supportFragmentManager, HISTORY_DIALOG_TAG)
            }
        }


    }

    private fun requestMovieList(query: String) {

        repositoryImpl.getSearchMovies(
            query = query,
            success = {
                movieAdapter.setMovieList(it.items)
            },
            failed = {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            })

    }
}
