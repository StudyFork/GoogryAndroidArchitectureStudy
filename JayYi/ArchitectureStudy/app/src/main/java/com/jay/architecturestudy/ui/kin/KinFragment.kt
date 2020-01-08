package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.databinding.FragmentKinBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.ui.BaseSearchContract
import com.jay.architecturestudy.util.then


class KinFragment : BaseFragment<FragmentKinBinding>(R.layout.fragment_kin), KinContract.View {
    override val presenter: KinContract.Presenter by lazy {
        KinPresenter(this, naverSearchRepository)
    }

    private lateinit var kinAdapter: KinAdapter

    override var viewType: BaseSearchContract.ViewType =
        BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                field = value
                binding.viewType = value
                binding.invalidateAll()
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        kinAdapter = KinAdapter()
        binding.recyclerView.adapter = kinAdapter

        binding.searchBar.onClickAction = { keyword ->
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
            binding.searchBar.keyword = keyword

            kins.isNotEmpty().then {
                kinAdapter.setData(kins)
            }

            viewType = when {
                kins.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Kin>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            kinAdapter.clear()
        } else {
            kinAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}