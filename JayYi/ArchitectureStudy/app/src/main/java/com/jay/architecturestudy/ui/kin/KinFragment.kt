package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then
import kotlinx.android.synthetic.main.fragment_kin.*

class KinFragment : BaseFragment(R.layout.fragment_kin), KinContract.View {
    override val presenter: KinContract.Presenter by lazy {
        KinPresenter(this, naverSearchRepository)
    }

    private lateinit var kinAdapter: KinAdapter

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

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, kins: List<Kin>) {
        keyword.isNotBlank().then {
            search_bar.keyword = keyword

            if (kins.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                kinAdapter.setData(kins)
            }
        }
    }

    override fun showEmptyResultView() {
        empty_result_view.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        recycler_view.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        empty_result_view.visibility = View.GONE
    }

    override fun hideResultListView() {
        recycler_view.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Kin>) {
        if (result.isEmpty()) {
            kinAdapter.clear()
        } else {
            kinAdapter.setData(result)
        }
    }
}