package wooooooak.com.studyapp.ui.kin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.databinding.ItemKinBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class KinListAdapter(
    private val itemListener: ItemListener<Kin>
) :
    BaseSearchListAdapter<Kin, KinListAdapter.KinViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KinViewHolder(
        ItemKinBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun bindItemViewHolder(holder: KinViewHolder, position: Int) {
        getItem(position)?.let { kin ->
            holder.bind(kin, View.OnClickListener {
                itemListener.renderWebView(kin.link)
            })
        }
    }

    inner class KinViewHolder(private val binding: ItemKinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kin: Kin, _onClickItemListener: View.OnClickListener) {
            with(binding) {
                item = kin
                onClickItemListener = _onClickItemListener
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Kin>() {
    override fun areItemsTheSame(oldItem: Kin, newItem: Kin) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Kin, newItem: Kin) =
        oldItem == newItem
}