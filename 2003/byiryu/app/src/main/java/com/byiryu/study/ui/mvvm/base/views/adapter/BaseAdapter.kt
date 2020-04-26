package com.byiryu.study.ui.mvvm.base.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.byiryu.study.wigets.OnViewClickListener
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.*

class BaseAdapter<T: Any> (
    val layoutSet: LayoutSet? = null,
    val itemSet: ItemSet,
    val diffCallback: DiffUtil.ItemCallback<T>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val helper by lazy {
        AsyncListDiffer<T>(
            OffsetListUpdateCallback(
                this
            ),
            AsyncDifferConfig.Builder<T>(diffCallback).build()
        )
    }

    fun submitList(list: List<T>?) {
        helper.submitList(list)
    }

    lateinit var lifecycleOwner: LifecycleOwner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == itemSet.layoutResId) {
            ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false),
                itemSet.onViewClickListener
            )
        } else {
            LayoutViewHolder(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            )
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LayoutViewHolder) {
            holder.binding?.run {
                layoutSet?.items?.get(position)!!.run {
                    setVariable(first, second)

                }
                executePendingBindings()
            }
            return
        }

        if (holder is ItemViewHolder) {
            holder.binding?.run {
                val item = getItem(position)
                itemSet.run {
                    setVariable(variableId, item)
                    items?.forEach{
                        setVariable(it.first, it.second)
                    }

                }
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasLayout() && getLayoutSetCount() > position) {
            layoutSet?.items?.get(position)!!.first
        } else {
            itemSet.layoutResId
        }
    }

    fun getItem(position: Int): T {
        return helper.currentList[position - getLayoutSetCount()]
    }

    override fun getItemCount(): Int = helper.currentList.size + getLayoutSetCount()

    private fun hasLayout(): Boolean = getLayoutSetCount() > 0

    private fun getLayoutSetCount(): Int = layoutSet?.items?.size ?: 0

    data class LayoutSet(
        val layoutResId: Int,
        val items: ArrayList<Pair<Int, Any>> = arrayListOf()
    )

    data class ItemSet(
        val layoutResId: Int,
        val variableId: Int,
        val items: ArrayList<Pair<Int, Any>>?,
        val onViewClickListener: OnViewClickListener?
    )

    class LayoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding: ViewDataBinding? = null
        init {
            try {
                binding = DataBindingUtil.bind(itemView)
            } catch (e: Exception) {
                Log.e("LayoutViewHolder", "$e")
            }
        }

    }

    class ItemViewHolder(itemView: View, onViewClickListener: OnViewClickListener?): RecyclerView.ViewHolder(itemView){
        var binding: ViewDataBinding? = null
        init {
            try {
                binding = DataBindingUtil.bind(itemView)

                if (binding != null && onViewClickListener != null) {
                    binding!!.setVariable(BR.itemClickListener, onViewClickListener)
                }

            } catch (e: Exception) {
                Log.e("ItemViewHolder", "$e")
            }
        }

    }

    private class OffsetListUpdateCallback(
        private val adapter: BaseAdapter<*>
    ) : ListUpdateCallback {

        fun offsetPosition(originalPosition: Int): Int {
            return originalPosition + adapter.getLayoutSetCount()
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeInserted(offsetPosition(position), count)
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyItemRangeRemoved(offsetPosition(position), count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyItemMoved(offsetPosition(fromPosition), offsetPosition(toPosition))
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(offsetPosition(position), count, payload)
        }
    }




}