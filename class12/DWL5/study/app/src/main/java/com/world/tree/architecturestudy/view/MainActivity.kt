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
import com.world.tree.architecturestudy.CommonApplication
import com.world.tree.architecturestudy.MovieContainer
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.databinding.ActivityMainBinding
import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.presenter.MovieContract
import com.world.tree.architecturestudy.presenter.MoviePresenterImpl
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
        val viewModel = MainViewModel(movieContainer.repository)
        binding.viewModel = viewModel

        viewModel.toastMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.toastMsg.get().isNullOrEmpty()) return
                Toast.makeText(applicationContext, viewModel.toastMsg.get(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun goToLink(link: String) {
        Log.d("Main", "goToLink() called link : $link")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link))
        )
    }
}
