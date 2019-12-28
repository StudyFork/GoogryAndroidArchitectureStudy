package com.example.architecturestudy.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieItem
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieContract.View {
    private lateinit var movieAdapter: MovieAdapter

    private val present : MovieContract.Present by lazy {
        MoviePresent(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()

        recycleview.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchMovie(edit)
            }
        }
    }

    private fun searchMovie(keyword: String) {
        present.searchMovie(keyword)
    }


    override fun showErrorMessage(message: String) {
        present.taskError(error(message))
    }

    override fun showResult(item: List<MovieItem>) {
        movieAdapter.update(item)
    }

}