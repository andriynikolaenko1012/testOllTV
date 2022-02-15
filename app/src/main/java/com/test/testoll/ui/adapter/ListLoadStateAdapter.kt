package com.test.testoll.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.testoll.databinding.RowLoadStateBinding

class ListLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ListLoadStateAdapter.ListLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ) = ListLoadStateViewHolder.create(parent, retry)

    class ListLoadStateViewHolder(
        private val binding: RowLoadStateBinding,
        retry: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }

            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error

        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): ListLoadStateViewHolder {
                val binding = RowLoadStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )

                return ListLoadStateViewHolder(binding, retry)
            }
        }
    }
}