package com.egiwon.architecturestudy.tabs.bottomsheet

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.ext.addCallback
import com.egiwon.architecturestudy.ext.doOnApplyWindowInsets
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.fg_history_sheet.*

class HistorySheetFragment : Fragment(R.layout.fg_history_sheet) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            val behavior = BottomSheetBehavior.from(history_sheet)
            val backCallback =
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, false) {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }

            val historyStartColor = history_sheet.context.getColor(R.color.history_200)
            val historyEndColor =
                history_sheet.context.getColorStateList(R.color.history_200).defaultColor

            val sheetBackground = MaterialShapeDrawable(
                ShapeAppearanceModel.builder(
                    history_sheet.context,
                    R.style.ShapeAppearance_History_MinimizedSheet,
                    0
                ).build()
            ).apply {
                fillColor = ColorStateList.valueOf(historyStartColor)
            }

            history_sheet.background = sheetBackground

            history_sheet.doOnLayout {
                val peek = behavior.peekHeight
                val maxTranslationX = (it.width - peek).toFloat()

                history_sheet.translationX = (history_sheet.width - peek).toFloat()

                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        backCallback.isEnabled = newState == BottomSheetBehavior.STATE_EXPANDED
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        history_sheet.translationX =
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

                        historylist_icon.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        sheet_expand.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        sheet_expand.visibility = if (slideOffset < 0.5) View.VISIBLE else View.GONE
                        historylist_title.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        collapse_historylist.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        historylist_title_divider.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        history_list.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                    }
                })
                history_sheet.doOnApplyWindowInsets { _, insets, _, _ ->
                    behavior.peekHeight = peek + insets.systemWindowInsetBottom
                }
                collapse_historylist.setOnClickListener {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                sheet_expand.setOnClickListener {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }

            }
        }

    }

}