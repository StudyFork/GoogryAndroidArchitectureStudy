package com.example.study.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.study.R
import com.example.study.RecyclerAdapter
import com.example.study.databinding.ActivityMainBinding
import com.example.study.viewmodel.MovieViewModel

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewAdapter: RecyclerAdapter
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewAdapter = RecyclerAdapter()
        init()
        binding.run {
            rvMain.apply {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    private fun init() {
        viewModel.run {
            fail.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
            })
            noQuery.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}





