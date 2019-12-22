package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.showToastMessage
import kotlinx.android.synthetic.main.fragemnt_movie.*

class KinFragment : BaseFragment(R.layout.fragemnt_kin), KinContract.View {
    override lateinit var presenter: KinContract.Presenter

    private lateinit var kinAdapter: KinAdapter

    private val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            kinAdapter = KinAdapter()
            recycler_view.run {
                adapter = kinAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter = KinPresenter(this, naverSearchRepository)
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Kin>) {
        kinAdapter.setData(result)
    }

    override fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}