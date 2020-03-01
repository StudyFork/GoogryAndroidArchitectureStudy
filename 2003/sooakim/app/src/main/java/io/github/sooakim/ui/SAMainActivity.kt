package io.github.sooakim.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import io.github.sooakim.R

class SAMainActivity : AppCompatActivity() {
    private lateinit var mSearchEdit: AppCompatEditText
    private lateinit var mSearchButton: AppCompatButton
    private lateinit var mSearchResultRecyclerView: RecyclerView

    private val mSearchResultAdapter: SAMainSearchResultAdapter = SAMainSearchResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
    }

    private fun initView() {
        mSearchEdit = findViewById(R.id.et_search)
        mSearchButton = findViewById(R.id.btn_search)
        mSearchResultRecyclerView = findViewById(R.id.rv_search_result)
    }

    private fun initRecyclerView() {
        mSearchResultRecyclerView.adapter = mSearchResultAdapter
    }
}
