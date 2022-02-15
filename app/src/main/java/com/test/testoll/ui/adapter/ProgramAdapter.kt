package com.test.testoll.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.testoll.R
import com.test.testoll.data.Item
import com.test.testoll.databinding.RowItemBinding
import com.test.testoll.databinding.RowLoadStateBinding
import kotlinx.android.synthetic.main.row_item.view.*

class ProgramAdapter(
    private val callback: ((item: Item, imgView: ImageView) -> Unit)? = null
): PagingDataAdapter<Item, ProgramAdapter.ProgramViewHolder>(diffCallback){

    inner class ProgramViewHolder(val recyclerViewItem: RowItemBinding) : RecyclerView.ViewHolder(recyclerViewItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        return ProgramViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val item = getItem(position)
        holder.recyclerViewItem.program = item
        holder.itemView.setOnClickListener {
            holder.itemView.thumbnail.transitionName = item?.icon.toString()
            callback?.invoke(item!!, holder.itemView.thumbnail)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id && oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id  && oldItem == newItem
            }
        }
    }
}