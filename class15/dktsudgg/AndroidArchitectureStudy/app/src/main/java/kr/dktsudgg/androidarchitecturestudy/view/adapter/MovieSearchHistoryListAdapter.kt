package kr.dktsudgg.androidarchitecturestudy.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.dktsudgg.androidarchitecturestudy.R
import kr.dktsudgg.androidarchitecturestudy.databinding.MovieSearchHistoryLayoutBinding

class MovieSearchHistoryListAdapter :
    RecyclerView.Adapter<MovieSearchHistoryListAdapter.ViewHolder>() {

    private val textList: MutableList<String> = mutableListOf()
    lateinit var clickedKeyword: String

    override fun getItemCount(): Int {
        return textList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieSearchHistoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(textList[position])
    }

    /**
     * 아이탬 목록을 갱신하는 메소드
     */
    fun refreshData(txtList: List<String>) {
        textList.clear()
        textList.addAll(txtList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MovieSearchHistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: String) {
            binding.textItem = item
            binding.viewHolder = this
        }

        fun click(clickedView: View) {
            when (clickedView.id) {
                R.id.usedKeyword -> {    // 클릭 시, 내용을 반환
                    clickedKeyword = clickedView.toString()
                    Toast.makeText(binding.root.context, binding.textItem, Toast.LENGTH_SHORT).show()
                    (binding.root.context as Activity).finish()
                }
                else -> {
                }
            }
        }

    }

}