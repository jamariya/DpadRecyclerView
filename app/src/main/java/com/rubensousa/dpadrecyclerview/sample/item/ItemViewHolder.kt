package com.rubensousa.dpadrecyclerview.sample.item

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubensousa.dpadrecyclerview.DpadViewHolder

class ItemViewHolder(
    root: View,
    private val textView: TextView
) : RecyclerView.ViewHolder(root), DpadViewHolder {

    private var clickListener: ItemClickListener? = null
    private val interpolator = AccelerateDecelerateInterpolator()

    init {
        itemView.setOnClickListener {
            clickListener?.onViewHolderClicked()
        }
        root.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                grow()
            } else {
                shrink()
            }
        }
    }

    fun bind(item: Int, listener: ItemClickListener?) {
        textView.text = item.toString()
        clickListener = listener
    }

    fun recycle() {
        clickListener = null
    }

    fun grow() {
        itemView.animate()
            .scaleX(1.1f)
            .scaleY(1.1f)
            .setInterpolator(interpolator)
            .duration = 500
    }

    fun shrink() {
        itemView.animate().cancel()
        itemView.scaleX = 1.0f
        itemView.scaleY = 1.0f
    }

    interface ItemClickListener {
        fun onViewHolderClicked()
    }

}
