package com.byiryu.study.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.network.NetworkService.retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var disposable: Disposable? = null

    private val adapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()

    }


    private fun bind() {

        recyclerView.adapter = adapter

        adapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        btn_search.setOnClickListener {
            val text = editText.text.toString()

            if (text.isEmpty()) {
                showMsg(R.string.msg_search_value)
            } else {
                disposable = retrofit.getSearchMovie(text)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading(true)
                    }
                    .doOnSuccess {
                        showLoading(false)
                    }
                    .doOnError {
                        showMsg(R.string.msg_error_loading)
                    }
                    .subscribe(
                        {
                            adapter.submitList(it.items)
                        },
                        { t ->
                            Log.e("MainActivity", t.toString())
                        }
                    )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.INVISIBLE
        }
    }

    override fun onDestroy() {

        disposable?.dispose()
        super.onDestroy()

    }

}
