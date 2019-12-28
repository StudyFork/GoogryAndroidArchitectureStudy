package com.example.architecturestudy.ui.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.Injection
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.repository.NaverSearchRepositoryImpl
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(), ImageContract.View {

    private lateinit var imageAdapter: ImageAdapter

    private val presenter : ImageContract.Presenter by lazy {
        ImagePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        presenter.taskSearch(keyword)
    }

    override fun showResult(item: List<ImageItem>) {
        imageAdapter.update(item)
    }

    override fun showErrorMessage(messeage: String) {
        presenter.taskError(error(messeage))
    }
}