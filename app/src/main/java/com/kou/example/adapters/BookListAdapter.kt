package com.kou.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kou.example.databinding.RowItemBinding
import com.kou.example.entities.Response
import com.kou.example.util.Utils
import com.kou.example.util.onEndLoading

/**
 * [ListAdapter] to show the list of book on main page
 */
class BookListAdapter(private var itemClickListener: ((item: Response.Book) -> Unit)? = null)
    : ListAdapter<Response.Book, BookListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.recycle()
    }

    fun setItemClickListener(listener: ((item: Response.Book) -> Unit)?) {
        this.itemClickListener = listener
    }

    inner class ViewHolder(private val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Response.Book) {
            binding.name.text = item.name
            binding.startDate.text = item.startDate
            binding.endDate.text = item.endDate
            Glide.with(binding.root.context)
                .load(item.icon)
                .onEndLoading {
                    Utils.swapVisibility(
                        100,
                        binding.logo,
                        binding.loadingLayout
                    )
                }
                .into(binding.logo)

            binding.root.setOnClickListener {
                this@BookListAdapter.itemClickListener?.invoke(item)
            }
        }

        fun recycle() {
            Glide.with(binding.root.context)
                .clear(binding.logo)
        }
    }

    /**
     * [DiffUtil.ItemCallback] for [Response.Book]
      */
    object DiffCallback: DiffUtil.ItemCallback<Response.Book>() {
        override fun areItemsTheSame(oldItem: Response.Book, newItem: Response.Book): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Response.Book, newItem: Response.Book): Boolean =
            oldItem.name == newItem.name &&
            oldItem.startDate == newItem.startDate &&
            oldItem.endDate == newItem.endDate &&
            oldItem.icon == newItem.icon
    }

}