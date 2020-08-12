package com.world.tree.architecturestudy.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {

    private val viewModel : MainViewModel by viewModels<MainViewModel>()

    private lateinit var adapter: MovieAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = MovieAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

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
