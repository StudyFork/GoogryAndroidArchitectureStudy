package com.lllccww.studyforkproject.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl
import com.lllccww.studyforkproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var start = 1
    private var total = 0
    private var display = 0
    private lateinit var movieListAdapter: MovieListAdapter


    private lateinit var binding: ActivityMainBinding

    private lateinit var vm: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        vm = MainViewModel(NaverMovieRepositoryImpl(MovieRemoteDataSourceImpl()))


        binding.vm = vm

        movieListAdapter = MovieListAdapter()
        binding.rvMovieList.adapter = movieListAdapter


        vm.toastString.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.toastString.get()?.let { toastMsg(it) }
            }

        })




        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as? LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = movieListAdapter.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    Log.d("fail : ", "스크롤 최하단")
                    if (start < total) {
                        //requestSearchMovie(start + 10)
                    } else {
                        Toast.makeText(this@MainActivity, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })

    }


    fun toastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}
