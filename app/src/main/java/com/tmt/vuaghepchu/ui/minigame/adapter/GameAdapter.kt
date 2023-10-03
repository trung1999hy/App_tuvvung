package com.tmt.vuaghepchu.ui.minigame.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.tmt.vuaghepchu.databinding.ItemRcvGameBinding
import com.tmt.vuaghepchu.ui.minigame.model.GameModel
import com.tmt.vuaghepchu.ui.base.recyclerview.BaseRecyclerView
import com.tmt.vuaghepchu.ui.base.recyclerview.BaseViewHolder

class GameAdapter(val context: Context, val itemClick: (GameModel) -> Unit) :
    BaseRecyclerView<GameModel, GameAdapter.ViewHolder>() {

    private var mList: ArrayList<GameModel> = ArrayList()

    inner class ViewHolder(private var binding: ItemRcvGameBinding) : BaseViewHolder<GameModel>(binding) {
        override fun bindViewHolder(data: GameModel) {
           Glide.with(context).load(data.logoGame).into(binding.LogoGame)
            itemView.setOnClickListener { itemClick.invoke(data) }
        }

    }

        @SuppressLint("NotifyDataSetChanged")
        override fun submitList(mList: ArrayList<GameModel>) {
            this.mList = mList
            notifyDataSetChanged()
        }

        override fun getListItem(): MutableList<GameModel>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRcvGameBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
    }
}