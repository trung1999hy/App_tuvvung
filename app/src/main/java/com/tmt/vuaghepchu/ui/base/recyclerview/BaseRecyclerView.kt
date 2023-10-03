package com.tmt.vuaghepchu.ui.base.recyclerview

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<T : Any, VH : BaseViewHolder<T>>() :
    RecyclerView.Adapter<VH>() {

    interface OnClickItem {
        fun isClickItem(view: View, position: Int, isCheck: Boolean)
    }

    abstract fun submitList(mList: ArrayList<T>)
    abstract fun getListItem(): MutableList<T>?

    open fun setAnimation(context: Context, viewToAnimate: View, anim: Int) {
        viewToAnimate.startAnimation(AnimationUtils.loadAnimation(context, anim))
    }
}