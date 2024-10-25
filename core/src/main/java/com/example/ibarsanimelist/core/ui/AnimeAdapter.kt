package com.example.ibarsanimelist.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibarsanimelist.core.R
import com.example.ibarsanimelist.core.databinding.ItemListAnimeBinding
import com.example.ibarsanimelist.core.domain.model.Anime
import java.util.ArrayList

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.ListViewHolder>() {

    private var listData = ArrayList<AnimeUIModel>()
    var onItemClick: ((AnimeUIModel) -> Unit)? = null

    fun setData(newListData: List<AnimeUIModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_anime, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListAnimeBinding.bind(itemView)
        fun bind(data: AnimeUIModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.titleJapanese
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}