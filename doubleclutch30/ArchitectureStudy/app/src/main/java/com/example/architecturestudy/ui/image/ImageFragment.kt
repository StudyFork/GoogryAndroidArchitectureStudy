package com.example.architecturestudy.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.repository.NaverSearchRepositoryImpl
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageAdapter = ImageAdapter()

        recycleview.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchImageList(edit)
            }
        }
    }

    private fun searchImageList(keyword : String) {
        naverSearchRepository.getImage(
            keyword = keyword,
            success = { responseImage -> imageAdapter.update(responseImage.items) },
            fail = { e -> error(message = e.toString()) }
        )
    }
}