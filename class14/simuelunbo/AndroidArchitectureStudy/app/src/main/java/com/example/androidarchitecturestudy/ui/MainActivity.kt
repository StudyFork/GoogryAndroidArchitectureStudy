package com.example.androidarchitecturestudy.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.local.LocalMovieDataImpl
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.remote.RemoteMovieDataImpl
import com.example.androidarchitecturestudy.data.repository.RepositoryMovie
import com.example.androidarchitecturestudy.data.repository.RepositoryMovieImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var movieAdapter: MovieAdapter = MovieAdapter()
    private val remoteMovieDataImpl = RemoteMovieDataImpl()
    private val localMovieDataImpl = LocalMovieDataImpl()
    private val repositoryMovieImpl = RepositoryMovieImpl(remoteMovieDataImpl,localMovieDataImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        btn_search.setOnClickListener {
            val searchText = et_search.text.toString()
            if (searchText.isEmpty()) {
                Toast.makeText(this, "검색을 원하시는 영화 제목을 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                hideKeyboard(this)
                progressBar.isVisible = true
                requestMovieInfo(searchText)
            }
        }
    }

    private fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search.windowToken, 0)
    }

    private fun initRecyclerView() {
        repositoryMovieImpl.getMovieData()?.let { movieAdapter.setMovieList(it) }
        rcv_result.adapter = movieAdapter
    }

    private fun requestMovieInfo(query: String) {
        repositoryMovieImpl.getSearchMovieList(
            query = query,
            success = {
                movieAdapter.setMovieList(it.items as ArrayList<Movie>)
                progressBar.isVisible = false
            },
            failed = {
                Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
                progressBar.isVisible = false
            })

    }
}