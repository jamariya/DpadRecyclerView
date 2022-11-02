package com.rubensousa.dpadrecyclerview.sample.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rubensousa.dpadrecyclerview.DpadViewHolder
import com.rubensousa.dpadrecyclerview.ViewHolderAlignment
import com.rubensousa.dpadrecyclerview.sample.R
import com.rubensousa.dpadrecyclerview.sample.databinding.AdapterListHeaderBinding

class ListHeaderAdapter : ListAdapter<String, ListHeaderAdapter.VH>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewHolder = VH(
            AdapterListHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        viewHolder.itemView.isFocusableInTouchMode = true
        viewHolder.itemView.isFocusable = true
        return viewHolder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return ListTypes.HEADER
    }

    class VH(
        binding: AdapterListHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root), DpadViewHolder {

        private val childAlignments = ArrayList<ViewHolderAlignment>()

        init {
            childAlignments.apply {
                add(
                    ViewHolderAlignment(
                        offset = 0,
                        offsetRatio = 0f,
                        alignmentViewId = R.id.subPosition0TextView,
                        focusViewId = R.id.subPosition0TextView
                    )
                )
                add(
                    ViewHolderAlignment(
                        offset = 0,
                        offsetRatio = 0f,
                        alignmentViewId = R.id.subPosition0TextView,
                        focusViewId = R.id.subPosition1TextView
                    )
                )
                add(
                    ViewHolderAlignment(
                        offset = 0,
                        offsetRatio = 0f,
                        alignmentViewId = R.id.subPosition0TextView,
                        focusViewId = R.id.subPosition2TextView
                    )
                )
            }
        }

        override fun getAlignments(): List<ViewHolderAlignment> {
            return childAlignments
        }

    }


}
