package com.example.kotlinapplication.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.ListNaverAdapter
import com.example.kotlinapplication.model.MovieItems
import com.example.kotlinapplication.model.ResponseItems
import com.example.kotlinapplication.network.RetrofitClient.client
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ListNaverAdapter.ItemListener {
    private var mService: RetrofitService? = null
    private var mResult: ResponseItems? = null
    private var mAdapter: ListNaverAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mService = client!!.create(RetrofitService::class.java)
        start()
    }

    private fun start() {
        buttonClick()
    }

    private fun buttonClick() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.isEmpty()) {
                Toast.makeText(context, "검색어를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "검색어 :" + home_search_edit.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                loadMovieData(home_search_edit.text.toString())
            }
        }
    }

    fun loadMovieData(query: String) {
        mService!!.GET_MOVIE_CALL(query)
            .observeOn(mainThread())
            .subscribeOn(io())
            .subscribe(this::handleResponse, this::HandleError)
    }

    private fun handleResponse(res: ResponseItems) {
        mResult = res
        Log.e("test", res.items.toString())
        mAdapter = ListNaverAdapter(this, res.items, context)
        home_recyclerview.layoutManager = LinearLayoutManager(
            activity, RecyclerView.VERTICAL, false
        )
        home_recyclerview.adapter = mAdapter
    }


    private fun HandleError(error: Throwable) {
        Log.d(TAG, "Error ${error.localizedMessage}")
    }

    override fun onItemClick(movieItems: MovieItems) {
        Toast.makeText(activity, movieItems.link, Toast.LENGTH_SHORT).show()
        val uri: Uri = Uri.parse(movieItems.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }
}
