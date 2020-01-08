package com.example.androidarchitecture.ui.image


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.databinding.FragmentImageBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import com.example.androidarchitecture.ui.base.ItemContract
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image),
    ItemContract.View<ImageData> {


    private lateinit var imageAdapter: ImageAdapter
    private val presenter by lazy { ImagePresent(this, naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycle.run {
            imageAdapter = ImageAdapter()
            adapter = imageAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            presenter.requestSearchHist()
        }

        binding.btnSearch.setOnClickListener {
            presenter.requestList(binding.editText.text.toString())

        }
    }


    override fun renderItems(items: List<ImageData>) {
        imageAdapter.setData(items)
        binding.executePendingBindings()
    }

    override fun errorToast(msg: String?) {
        msg?.let { requireContext().toast(it) }
    }

    override fun blankInputText() {
        requireContext().toast(getString(R.string.black_input_text))
    }

    override fun inputKeyword(msg: String?) {
        binding.lastInputText = msg
    }


    override fun isListEmpty(visible: Boolean) {
        binding.isListEmpty = visible
    }

}
