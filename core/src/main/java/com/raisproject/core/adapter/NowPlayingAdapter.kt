package com.raisproject.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raisproject.core.databinding.ItemNowPlayingBinding
import com.raisproject.core.domain.model.Movie

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(val binding: ItemNowPlayingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                tvTitle.text = data.title
                tvOverview.text = data.overview
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + data.poster_path)
                    .into(ivPoster)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        return NowPlayingViewHolder(ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(listData[position])
    }
}