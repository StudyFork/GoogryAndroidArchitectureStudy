package com.example.studyfork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studyfork.databinding.ActivityMainBinding
import com.example.studyfork.model.RemoteDataSourceImpl
import com.example.studyfork.model.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val repository by lazy {
        RepositoryImpl(RemoteDataSourceImpl())
    }
    private val recyclerAdapter by lazy { MovieRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recMovie.adapter = recyclerAdapter

        binding.btnSearch.setOnClickListener {
            binding.edtQuery.text?.run {
                repository.searchMovie(this.toString(),
                    {
                        it.items.run {
                            recyclerAdapter.itemChange(this)
                        }
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }

    override fun onDestroy() {
        repository.disposableClear()
        super.onDestroy()
    }
}