package com.faraji.mvibase.example.ui.image.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.faraji.mvibase.example.databinding.ItemImageBinding
import com.faraji.mvibase.example.presentation.model.ImageListItem

private class DiffCallback : DiffUtil.ItemCallback<ImageListItem>() {

    override fun areItemsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean {
        return oldItem == newItem
    }
}


internal class ImageListAdapter(
    val onItemClicked: (ImageListItem) -> Unit,
) : ListAdapter<ImageListItem, ImageListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClicked(currentList[adapterPosition])
            }
        }

        fun bind(item: ImageListItem) = with(itemView) {
            binding.imgItemCatImage.load(item.url)
        }
    }
}