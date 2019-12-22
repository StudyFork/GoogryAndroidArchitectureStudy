package com.example.androidarchitecture.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.repository.NaverRepo
import com.example.androidarchitecture.data.response.MovieData
import com.example.androidarchitecture.ui.base.NaverContract
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), NaverContract.View<MovieData> {

    private lateinit var movieAdapter: MovieAdapter
    private val presenter by lazy { MoviePresenter(this, NaverRepo()) }


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
                presenter.requestList(edit_text.text.toString())
            }
        }
    }

    override fun renderItems(items: List<MovieData>) {
        movieAdapter.setData(items)
    }

    override fun errorToast(msg: String?) {

        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }


}
