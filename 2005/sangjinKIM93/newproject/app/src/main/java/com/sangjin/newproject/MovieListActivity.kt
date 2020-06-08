package com.sangjin.newproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.sangjin.newproject.adapter.MovieListAdapter
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.repository.NaverMoviesRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.local.RoomDb
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import com.sangjin.newproject.databinding.ActivityMovieListBinding
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter
    private val naverMoviesRepositoryImpl by lazy {
        NaverMoviesRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(RoomDb.getInstance(applicationContext))
        )
    }

    private val viewModel by lazy {
        MovieListViewModel(naverMoviesRepositoryImpl)
    }


    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        binding.viewModel = viewModel

        setRecyclerView()
        showKeyPad()

    }


    //**리사이클러뷰 셋팅
    private fun setRecyclerView() {

        //각 항목 클릭시 이벤트 처리
        val onItemClickListener: ((Int) -> Unit) = { position ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(movieListAdapter.getMovieList().get(position).link)
            )
            startActivity(intent)
        }

        movieListAdapter = MovieListAdapter(onItemClickListener)
        binding.movieListView.adapter = movieListAdapter
    }


    //**키패드 보여주기
    private fun showKeyPad() {
        binding.movieNameET.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    //**키패드 숨기기
    private fun hideKeyPad() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(movieNameET.windowToken, 0)
    }



    //**키패드 셋팅
    companion object {
        @BindingAdapter("setKeypad")
        @JvmStatic
        fun EditText.setKeypad(viewModel: MovieListViewModel) {
            this.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    true
                } else {
                    false
                }
            }
        }
    }
}
