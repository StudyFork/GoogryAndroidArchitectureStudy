package com.world.tree.architecturestudy.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.world.tree.architecturestudy.CommonApplication
import com.world.tree.architecturestudy.MovieContainer
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var movieContainer: MovieContainer
    private lateinit var adapter: MovieAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        movieContainer = (application as CommonApplication).movieContainer
        adapter = MovieAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter
        val viewModel = MainViewModelProvider(movieContainer.repository).create(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.toastMsg.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun goToLink(link: String) {
        Log.d("Main", "goToLink() called link : $link")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link))
        )
    }
}
