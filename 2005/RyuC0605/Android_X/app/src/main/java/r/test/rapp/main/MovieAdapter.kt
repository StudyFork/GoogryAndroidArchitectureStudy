package r.test.rapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.ObservableList
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.databinding.RowContentBinding

/**
 * 리스트 뷰의 커스텀 어댑터.
 */
class MovieAdapter(vm : MainViewModel) : BaseAdapter() {
    init {
        vm.movies.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Item>>() {
            override fun onChanged(sender: ObservableList<Item>?) {
                sender?.let {
                    movieList.clear()
                    movieList.addAll(it.toList())
                }
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableList<Item>?,
                positionStart: Int,
                itemCount: Int
            ) {
                for (index in 0..(itemCount - 1)) {
                    movieList.removeAt(positionStart)
                }
                notifyDataSetChanged()
            }

            override fun onItemRangeMoved(
                sender: ObservableList<Item>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                val moveItems = movieList.subList(fromPosition, fromPosition + (itemCount - 1))
                moveItems.map { movieList.remove(it) }
                moveItems.forEachIndexed { index, customRecyclerItem ->
                    movieList.add(toPosition + index, customRecyclerItem)
                }
                notifyDataSetChanged()
            }

            override fun onItemRangeInserted(
                sender: ObservableList<Item>?,
                positionStart: Int,
                itemCount: Int
            ) {
                val insertItems = sender?.toList() ?: return
                movieList.addAll(positionStart, insertItems.subList(positionStart, positionStart + itemCount))
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableList<Item>?,
                positionStart: Int,
                itemCount: Int
            ) {
                val changedItems = sender?.toList() ?: return
                changedItems.subList(positionStart, positionStart + itemCount).forEachIndexed { index, customRecyclerItem ->
                    movieList[positionStart + index] = customRecyclerItem
                }
                notifyDataSetChanged()

            }
        })
    }
    private val movieList: ArrayList<Item> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(parent?.context)
        val rowView: View = convertView ?: inflater.inflate(R.layout.row_content, parent, false)

        val holder = (convertView?.tag as? ViewHolder)
            ?: run {
                ViewHolder(rowView)
                    .also { rowView.tag = it }
            }

        val item = movieList[position]

        holder.binding.movie = item

        return holder.binding.root
    }

    fun getMovieList(): ArrayList<Item> {
        return movieList
    }

    private class ViewHolder(view: View) {
        val binding: RowContentBinding = RowContentBinding.bind(view)
    }

    override fun getItem(position: Int): Item {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movieList.size
    }
}