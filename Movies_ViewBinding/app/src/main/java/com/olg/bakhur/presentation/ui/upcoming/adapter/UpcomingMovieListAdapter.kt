package com.olg.bakhur.presentation.ui.upcoming.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.databinding.ItemNowPlayingMovieBinding as Binding

class UpcomingMovieListAdapter(
    private val openDetails: (itemId: Int) -> Unit
    ) : RecyclerView.Adapter<UpcomingMovieListAdapter.ItemViewHolder>() {

    var upcomingMoviesList: MutableList<UpcomingMovie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = Binding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(itemBinding, openDetails)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(upcomingMoviesList[position])
    }

    override fun getItemCount(): Int = upcomingMoviesList.size

    fun setData(newUpcomingMoviesList: MutableList<UpcomingMovie>) {
        upcomingMoviesList.apply {
            clear()
            addAll(newUpcomingMoviesList)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        private val binding: Binding,
        private val openDetails: (itemId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(upcomingMovie: UpcomingMovie) {
            with(itemView){
                binding.textViewMovieTitleItem.text = upcomingMovie.title
                binding.textViewAverageVoteItem.text = upcomingMovie.voteAverage.toString()

                val posterAddress: String = AppConstants.posterBaseUrl + upcomingMovie.posterPath
                Glide.with(itemView.context)
                    .load(posterAddress)
                    .into(binding.imageViewPosterItem)
                setOnClickListener { openDetails(upcomingMovie.id) }
            }
        }
    }
}