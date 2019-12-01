package com.jay.architecturestudy.ui.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.ResponseImages
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.util.toPx
import com.jay.architecturestudy.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragemnt_movie.*
import kotlinx.android.synthetic.main.view_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragemnt_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_btn.setOnClickListener {
            val keyword = search_editor.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(activity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                search(keyword)
            }
        }

        activity?.let { activity ->
            imageAdapter = ImageAdapter(activity)
                .also {
                    recycler_view.apply {
                        adapter = it
                        layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                        addItemDecoration(SpacesItemDecoration(12.toPx(), 6.toPx(), 11.toPx()))
                    }
                }
        }
    }

    private fun search(keyword: String) {
        Api.getImages(keyword)
            .enqueue(object : Callback<ResponseImages> {
                override fun onFailure(call: Call<ResponseImages>, t: Throwable) {
                    Log.e("Image", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseImages>,
                    response: Response<ResponseImages>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        imageAdapter.setData(body.images)
                    }
                }

            })
    }
}