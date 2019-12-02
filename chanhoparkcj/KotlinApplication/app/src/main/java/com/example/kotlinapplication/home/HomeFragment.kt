package com.example.kotlinapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kotlinapplication.R
import com.example.kotlinapplication.model.ResponseItems
import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitClient.client
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io


class HomeFragment : Fragment() {
    private var mHomeSearchBtn: Button? = null
    private var mHomeSearchEdit: EditText? = null
    private var mHomeRecyclerView: RecyclerView? = null
    private var mHomeTypeBtn: Button? = null
    private var mService: RetrofitService? = null
    private var mResult: ResponseItems? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeSearchBtn = view.findViewById(R.id.home_search_btn)
        mHomeRecyclerView = view.findViewById(R.id.home_recyclerview)
        mHomeSearchEdit = view.findViewById(R.id.home_search_edit)
        mHomeTypeBtn = view.findViewById(R.id.home_type_btn)
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

    }

    private fun HandleError(error: Throwable) {
        Log.d(TAG, "Error ${error.localizedMessage}")
    }
}
