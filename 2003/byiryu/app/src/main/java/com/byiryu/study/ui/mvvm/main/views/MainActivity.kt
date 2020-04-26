package com.byiryu.study.ui.mvvm.main.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.byiryu.study.BR
import com.byiryu.study.R
import com.byiryu.study.databinding.ActivityMainBinding
import com.byiryu.study.model.Movie
import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.base.views.adapter.BaseAdapter
import com.byiryu.study.ui.mvvm.main.viewmodel.MainViewModel
import com.byiryu.study.wigets.OnViewClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(){

    private val viewModel: MainViewModel by viewModel()

    private val adapter: BaseAdapter<Movie> =
        BaseAdapter(
            itemSet = BaseAdapter.ItemSet(
                R.layout.view_main_item, BR.movieItem, null,
                onViewClickListener = object : OnViewClickListener {
                    override fun onclick(data: Any) {
                        if (data !is Movie) {
                            return
                        }

                        goActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.link)))
                    }

                }
            ),
            diffCallback = object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity

            vm = viewModel

            adapter = this@MainActivity.adapter.apply {
                lifecycleOwner = this@MainActivity
            }

            viewModel.apply {
                status.observe(this@MainActivity, Observer {
                    it?.run {
                        if (!first) {
                            when (val str = second) {
                                is Int -> showMsg(str)
                                is String -> showMsg(str)
                            }
                        } else {
                            return@run
                        }
                    }
                })

                movieData.observe(this@MainActivity, Observer {
                    this@MainActivity.adapter.submitList(it)
                })
            }
        }

        setContentView(binding.root)
    }
}