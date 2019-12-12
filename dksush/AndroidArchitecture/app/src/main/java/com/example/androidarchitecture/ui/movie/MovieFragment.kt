package com.example.androidarchitecture.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.repository.NaverRepo
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private val naverRepoInterface: NaverRepoInterface by lazy {
        NaverRepo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            movieAdapter = MovieAdapter()
                .also {
                    recycle.adapter = it
                    recycle.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
        }



        btn_search.setOnClickListener {
            if (edit_text != null) {
                requestMovieList(edit_text.text.toString())

            }
        }
    }

    private fun requestMovieList(text: String) {
        naverRepoInterface.getMovie(text, 1, 10,
            success = {
                movieAdapter.setData(it)
            }, fail = {
            })

    }


}
