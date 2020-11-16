package com.example.hw2_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.data.MovieList
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {

    private lateinit var editTextMovieName : EditText
    private lateinit var buttonSearch : Button
    private lateinit var recyclerView : RecyclerView

    private val adapter = RecyclerViewAdapter()

    private val movieRepositoryImp = MovieRepositoryImpl()

    private lateinit var presenter: Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonSearch = findViewById<Button>(R.id.button_search)
        editTextMovieName = findViewById<EditText>(R.id.edittext_movie_name)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        presenter = Presenter(this, movieRepositoryImp)

        buttonSearch.findViewById<Button>(R.id.button_search).setOnClickListener {
            presenter.requestMovieListToRepo(editTextMovieName.text.toString())
        }
    }

    override fun showErrorEmptyQuery() {
        Toast.makeText(this, "영화제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showMovieList(movieList: MovieList) {
        hideKeyboard()
        runOnUiThread{
            adapter.movieListChange(movieList.items)
        }
    }

    override fun showErrorRespondMsg(t: Throwable) {
        Log.e("showErrorRespondMsg",t.stackTraceToString())
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextMovieName.windowToken, 0)
    }

}