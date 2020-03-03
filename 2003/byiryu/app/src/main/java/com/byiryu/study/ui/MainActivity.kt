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

class MainActivity : BaseActivity(), MainRecyclerAdapter.OnClickListener {


    lateinit var text: String

    private var disposable: Disposable? = null
    // false 로딩중 true 완
    private var isLoading = false

    private val adapter = MainRecyclerAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()

    }


    fun bind() {

        recyclerView.adapter = adapter
        adapter.setOnclickListener(this)

        btn_search.setOnClickListener {
            text = editText.text.toString()

            if (text.isEmpty()) {
                showMsg("검색어를 입력해주세요.")
            } else {
                disposable = retrofit.getSearchMovie(text)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        loading(false)
                    }
                    .doOnSuccess {
                        loading(true)
                    }
                    .doOnError {

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

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            loading.visibility = View.INVISIBLE
        } else {
            loading.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }

    }

    override fun onClick(url: String) {

        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
