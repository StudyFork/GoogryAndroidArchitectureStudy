package io.github.sooakim.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.sooakim.network.model.SAModel

typealias OnRecyclerViewItemClick<E> = ((E) -> Unit)

abstract class SARecyclerViewAdapter<E, VH>(
    private val onItemClick: OnRecyclerViewItemClick<E>? = null
) : ListAdapter<E, VH>(object : DiffUtil.ItemCallback<E>() {
    override fun areItemsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: E, newItem: E): Boolean {
        return oldItem == newItem
    }
}) where E : SAIdentifiable, E : SAModel, VH : SAViewHolder {
    private lateinit var mLayoutInflater: LayoutInflater

    private fun provideLayoutInflater(context: Context): LayoutInflater {
        if (!::mLayoutInflater.isInitialized) mLayoutInflater = LayoutInflater.from(context)
        return mLayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return this.onCreateViewHolder(provideLayoutInflater(parent.context), parent, viewType)
            .also {
                if (onItemClick == null) return@also
                else it.itemView.setOnClickListener { _ ->
                    val currentItem =
                        currentList.getOrNull(it.adapterPosition) ?: return@setOnClickListener
                    onItemClick.invoke(currentItem)
                }
            }
    }

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VH

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = currentList.getOrNull(position) ?: return
        (holder as? SAViewHolderLifecycle<E>)?.onBind(currentItem)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        (holder as? SAViewHolderLifecycle<E>)?.onRecycle()
    }
}