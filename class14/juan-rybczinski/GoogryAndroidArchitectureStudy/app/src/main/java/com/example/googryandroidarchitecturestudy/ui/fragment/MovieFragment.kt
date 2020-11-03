package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.model.MovieResponse
import com.example.googryandroidarchitecturestudy.network.MovieApi
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MovieFragment : Fragment(), CoroutineScope {

    private val TAG = this::class.java.simpleName

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

//        search_button.setOnClickListener {
//            Toast.makeText(requireContext(), "검색", Toast.LENGTH_SHORT).show()
//        }

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            launch {
                try {
                    val movieResponse = MovieApi.movieService.searchMovies(search_text.text.toString())
                    Log.i(TAG, movieResponse.toString())
                } catch (e: Exception) {
                    Log.e(TAG, e.message)
                }
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()

    }

}