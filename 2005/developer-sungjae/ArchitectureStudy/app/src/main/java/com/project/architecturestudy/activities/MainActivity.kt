package com.project.architecturestudy.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var adapter: SearchAdapter? = null
    private val naverMovieRepositoryImpl by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl()
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()
        NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener {
            if (et_search.text.toString().isEmpty()) {
                return@setOnClickListener
            }
            naverMovieRepositoryImpl.getMovieList(et_search.text.toString(), adapter,
                SuccessMsg = { toast(getString(R.string.get_data_success)) },
                FailureMsg = { toast(getString(R.string.get_data_failure)) })
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        adapter = SearchAdapter()
        listview_movie.adapter = adapter

        adapter?.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }
    }
}
