package com.olg.bakhur.presentation.ui.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.databinding.ItemPopularMovieBinding as Binding
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.presentation.ui.common.OnItemClickListener


class PopularMovieListAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PopularMovieListAdapter.ItemViewHolder>() {

    var popularMoviesList: MutableList<PopularMovie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = Binding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(popularMoviesList[position], onItemClickListener)
    }

    override fun getItemCount(): Int = popularMoviesList.size

    fun setData(newMoviesList: MutableList<PopularMovie>) {
        popularMoviesList.apply {
            clear()
            addAll(newMoviesList)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popularMovie: PopularMovie, onItemClickListener: OnItemClickListener) {
            with(itemView){
                binding.textViewMovieTitleItem.text = popularMovie.title
                binding.textViewAverageVoteItem.text = popularMovie.voteAverage.toString()

                val posterAddress: String = AppConstants.posterBaseUrl + popularMovie.posterPath
                Glide.with(itemView.context)
                    .load(posterAddress)
                    .into(binding.imageViewPosterItem)

                setOnClickListener { onItemClickListener.openDetails(popularMovie.id) }
            }
        }
    }
}