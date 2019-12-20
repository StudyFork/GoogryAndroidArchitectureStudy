package com.example.androidarchitecture.ui.image


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.repository.NaverRepo
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private val naverRepo = NaverRepo()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        activity?.let {
            imageAdapter = ImageAdapter()
                .also {
                    recycle.adapter = it
                    recycle.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
        }



        btn_search.setOnClickListener {
            if (edit_text != null) {
                requestImageList(edit_text.text.toString())
            }
        }
    }


    private fun requestImageList(text: String) {
        naverRepo.getImage(text, 1, 10,
            success = {
                imageAdapter.setData(it)
            }, fail = {

            })

    }

}
