package com.egiwon.architecturestudy.tabs.bottomsheet

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.NaverDataRepositoryImpl
import com.egiwon.architecturestudy.data.source.local.ContentDataBase
import com.egiwon.architecturestudy.data.source.local.NaverLocalDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import com.egiwon.architecturestudy.databinding.FgHistorySheetBinding
import com.egiwon.architecturestudy.ext.addCallback
import com.egiwon.architecturestudy.ext.doOnApplyWindowInsets
import com.egiwon.architecturestudy.ext.getColor
import com.egiwon.architecturestudy.ext.getColorStateList
import com.egiwon.architecturestudy.tabs.ContentsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class HistorySheetFragment :
    BaseFragment<FgHistorySheetBinding, HistoryContract.Presenter>(
        R.layout.fg_history_sheet
    ), HistoryContract.View {

    override val presenter: HistoryContract.Presenter by lazy {
        HistoryPresenter(
            this,
            NaverDataRepositoryImpl.getInstance(
                NaverRemoteDataSource.getInstance(),
                NaverLocalDataSource.getInstance(
                    ContentDataBase.getInstance(requireActivity().applicationContext).contentDao()
                )
            )
        )
    }

    private val onClick: (String) -> Unit = { query ->
        getTab()?.let { tab ->
            (parentFragment as? ContentsFragment)?.loadContentsByHistoryQuery(tab, query)
        }

        BottomSheetBehavior.from(binding.historySheet).state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private val historyAdapter = HistoryAdapter(onClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            with(binding) {
                val behavior = BottomSheetBehavior.from(historySheet)
                val backCallback =
                    requireActivity().onBackPressedDispatcher.addCallback(
                        viewLifecycleOwner,
                        false
                    ) {
                        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }

                val historyStartColor = historySheet.getColor(R.color.history_200)
                val historyEndColor =
                    historySheet.getColorStateList(R.color.history_200).defaultColor

                val sheetBackground = MaterialShapeDrawable(
                    ShapeAppearanceModel.builder(
                        context,
                        R.style.ShapeAppearance_History_MinimizedSheet,
                        0
                    ).build()
                ).apply {
                    fillColor = ColorStateList.valueOf(historyStartColor)
                }

                historySheet.background = sheetBackground

                historySheet.doOnLayout {
                    val peek = behavior.peekHeight
                    val maxTranslationX = (it.width - peek).toFloat()

                    historySheet.translationX = (historySheet.width - peek).toFloat()

                    behavior.addBottomSheetCallback(object :
                        BottomSheetBehavior.BottomSheetCallback() {
                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            backCallback.isEnabled = newState == BottomSheetBehavior.STATE_EXPANDED
                            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                                getTab()?.let { tab ->
                                    presenter.getSearchQueryHistory(tab)
                                }
                            }
                        }

                        override fun onSlide(bottomSheet: View, slideOffset: Float) {
                            historySheet.translationX =
                                lerp(maxTranslationX, 0f, 0f, 0.15f, slideOffset)
                            sheetBackground.interpolation = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                            sheetBackground.fillColor = ColorStateList.valueOf(
                                lerpArgb(
                                    historyStartColor,
                                    historyEndColor,
                                    0f,
                                    0.3f,
                                    slideOffset
                                )
                            )

                            binding.slideOffset = slideOffset
                            historylistIcon.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                            sheetExpand.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                            historylistTitle.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                            collapseHistorylist.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                            historylistTitleDivider.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                            historyList.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        }
                    })
                    historySheet.doOnApplyWindowInsets { _, insets, _, _ ->
                        behavior.peekHeight = peek + insets.systemWindowInsetBottom
                    }

                } // end of doLayout

                collapseHistorylist.setOnClickListener {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                sheetExpand.setOnClickListener {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
                historyList.adapter = historyAdapter
            } // end of with

        } // end of apply

    }

    private fun getTab(): Tab? = (parentFragment as? ContentsFragment)?.tab

    override fun showSearchQueryHistory(history: List<String>) {
        (binding.historyList.adapter as? HistoryAdapter)?.replaceAll(history)
    }

    override fun showLoading() = Unit
    override fun hideLoading() = Unit
}