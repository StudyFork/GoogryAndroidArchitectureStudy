package com.example.kotlinapplication.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Toast
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
import java.net.URI
import java.util.*


class HomeFragment : Fragment(), ListNaverAdapter.ItemListener {
    private var mHomeSearchBtn: Button? = null
    private var mHomeSearchEdit: EditText? = null
    private var mHomeRecyclerView: RecyclerView? = null
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
        mHomeSearchBtn = view.findViewById(R.id.home_search_btn)
        mHomeRecyclerView = view.findViewById(R.id.home_recyclerview)
        mHomeSearchEdit = view.findViewById(R.id.home_search_edit)
        mService = client!!.create(RetrofitService::class.java)
        start()
    }

    private fun start() {
        buttonClick()
    }

    private fun buttonClick() {
        mHomeSearchBtn!!.setOnClickListener {
            if (mHomeSearchEdit!!.text.isEmpty()) {
                Toast.makeText(context, "검색어를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "검색어 :" + mHomeSearchEdit!!.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                loadMovieData(mHomeSearchEdit!!.text.toString())
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
        mHomeRecyclerView!!.layoutManager = LinearLayoutManager(
            activity, RecyclerView.VERTICAL, false
        )
        mHomeRecyclerView!!.adapter = mAdapter
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
