package com.example.aas.ui.savedquerydialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.aas.base.BaseDialogFragment

class SavedQueryDialogFragment(private val historySelectionListener: HistorySelectionListener) :
    BaseDialogFragment<SavedQueryContract.Presenter>(), SavedQueryContract.View {

    override val presenter: SavedQueryContract.Presenter by lazy { SavedQueryPresenter(this) }
    private lateinit var searchHistory: List<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        searchHistory = presenter.getSavedQuery(arguments?.getStringArray(HISTORY_LIST))

        return requireActivity().let {
            AlertDialog.Builder(it)
                .setTitle("최근 5개 검색어")
                .setItems(searchHistory.toTypedArray()) { _, idx ->
                    historySelectionListener.onHistorySelection(searchHistory[idx])
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