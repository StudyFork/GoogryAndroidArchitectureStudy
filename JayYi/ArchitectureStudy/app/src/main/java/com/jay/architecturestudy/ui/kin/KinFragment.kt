package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.databinding.FragmentKinBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then


class KinFragment : BaseFragment<FragmentKinBinding, KinViewModel>(R.layout.fragment_kin) {

    override val viewModel: KinViewModel by lazy {
        KinViewModel(naverSearchRepository)
    }

    private lateinit var kinAdapter: KinAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            kinAdapter = KinAdapter()
            binding.recyclerView.run {
                adapter = kinAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        binding.vm = viewModel
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}