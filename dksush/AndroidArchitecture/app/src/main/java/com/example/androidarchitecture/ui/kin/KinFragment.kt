package com.example.androidarchitecture.ui.kin


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.databinding.FragmentKinBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import com.example.androidarchitecture.ui.base.ItemContract
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class KinFragment : BaseFragment<FragmentKinBinding>(R.layout.fragment_kin),
    ItemContract.View<KinData> {
    private lateinit var kinAdapter: KinAdapter
    private val presenter by lazy { KinPresent(this, naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycle.run {
            kinAdapter = KinAdapter()
            adapter = kinAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            presenter.requestSearchHist()
        }

        binding.btnSearch.setOnClickListener {
            presenter.requestList(binding.editText.text.toString())

        }
    }


    override fun renderItems(items: List<KinData>) {
        kinAdapter.setData(items)
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
