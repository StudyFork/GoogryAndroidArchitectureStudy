package com.example.handnew04.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.handnew04.R
import com.example.handnew04.adapter.MovieRecyclerAdapter
import com.example.handnew04.databinding.ActivityMainBinding
import com.example.handnew04.network.NetworkManager
import com.example.handnew04.ui.movie.MovieDetailActivity


class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: MovieRecyclerAdapter
    lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by lazy { MainViewModel(NetworkManager(this@MainActivity.application)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )


        initBinding()
        initailize()
        initObserveCallBack()
    }


    private fun initailize() {
        recyclerAdapter = MovieRecyclerAdapter()

        recyclerAdapter.setItemClickListener(object :
            MovieRecyclerAdapter.ItemClickListener {
            override fun onClick(position: Int) {
                showMovieDetailActivity(position)
            }
        })

        binding.rcvMovies.adapter = recyclerAdapter
    }

    private fun initBinding() {
        binding.activity = this@MainActivity
        binding.viewModel = viewModel
    }

    fun searchButtonClick() {
        val inputText = binding.etSearchBar.text.toString()
        viewModel.serchMovie(inputText)
    }

    private fun initObserveCallBack() {
        with(viewModel) {
            isEmptyResult.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showEmptyResult()
                }

            })
            isNetworkRunning.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showNotConnectedNetwork()
                }
            })
            isInputTextLengthZero.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showInputLengthZero()
                }

            })
            failMessage.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showFailSearchMovie(failMessage.toString())
                }

            })
        }
    }

    private fun showMovieDetailActivity(position: Int) {
        val nextIntent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        nextIntent.putExtra(
            getString(R.string.movieLink),
            recyclerAdapter.getMovieLink(position)
        )
        startActivity(nextIntent)
    }

    private fun showEmptyResult() {
        Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun showNotConnectedNetwork() {
        Toast.makeText(this@MainActivity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
    }

    private fun showInputLengthZero() {
        Toast.makeText(this@MainActivity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    private fun showFailSearchMovie(message: String?) {
        Toast.makeText(this@MainActivity, "검색에 실패하였습니다. MSG : $message", Toast.LENGTH_SHORT).show()
    }
}
