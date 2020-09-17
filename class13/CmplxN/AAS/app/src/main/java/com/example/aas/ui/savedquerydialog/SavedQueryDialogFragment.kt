package com.example.aas.ui.savedquerydialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.aas.base.BaseDialogFragment

class SavedQueryDialogFragment(private val historySelectionListener: HistorySelectionListener) :
    BaseDialogFragment<SavedQueryContract.Presenter>(), SavedQueryContract.View {

    override val presenter: SavedQueryContract.Presenter by lazy { SavedQueryPresenter(this) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        presenter.getSavedQuery(arguments?.getStringArray(HISTORY_LIST))

    override fun buildDialog(savedQuery: Array<String>): Dialog {
        return requireActivity().let {
            AlertDialog.Builder(it)
                .setTitle("최근 5개 검색어")
                .setItems(savedQuery) { _, idx ->
                    historySelectionListener.onHistorySelection(savedQuery[idx])
                }
                .create()
        }
    }

    interface HistorySelectionListener {
        fun onHistorySelection(query: String)
    }

    companion object {
        @Volatile
        private var INSTANCE: SavedQueryDialogFragment? = null

        const val HISTORY_LIST = "historyList"
        const val TAG = "history"

        fun getInstance(historySelectionListener: HistorySelectionListener): SavedQueryDialogFragment =
            INSTANCE ?: synchronized(this) {
                SavedQueryDialogFragment(historySelectionListener).also { INSTANCE = it }
            }
    }
}