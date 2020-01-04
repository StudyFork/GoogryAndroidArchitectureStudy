package com.example.androidarchitecture.ui.kin


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.ui.base.BaseSearchFragment
import com.example.androidarchitecture.ui.base.ItemContract
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class KinFragment : BaseSearchFragment(R.layout.fragment_kin), ItemContract.View<KinData> {


    private lateinit var kinAdapter: KinAdapter
    private val presenter by lazy { KinPresent(this, naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            kinAdapter = KinAdapter()
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

        lifecycleScope.launch {
            presenter.requestSearchHist()
        }

        btn_search.setOnClickListener {
            presenter.requestList(edit_text.text.toString())

        }
    }


    override fun renderItems(items: List<KinData>) {
        kinAdapter.setData(items)
    }

    override fun errorToast(msg: String?) {
        msg?.let { requireContext().toast(it) }
    }

    override fun blankInputText() {
        requireContext().toast(getString(R.string.black_input_text))
    }

    override fun inputKeyword(msg: String?) {
        edit_text.setText(msg)
    }

    override fun goneEmptyText() {
        tv_empty_itme.visibility = View.GONE
    }


}
