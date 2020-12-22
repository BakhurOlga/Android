package com.olg.bakhur.presentation.ui.now_playing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.databinding.ItemNowPlayingMovieBinding as Binding

class NowPlayingMovieListAdapter(
    private val openDetails: (itemId: Int) -> Unit
) : RecyclerView.Adapter<NowPlayingMovieListAdapter.ItemViewHolder>() {

    var nowPlayingMoviesList: MutableList<NowPlayingMovie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = Binding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(itemBinding, openDetails)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position])
    }

    override fun getItemCount(): Int = nowPlayingMoviesList.size

    fun setData(newNowPlayingMovies: MutableList<NowPlayingMovie>) {
        nowPlayingMoviesList.apply {
            clear()
            addAll(newNowPlayingMovies)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        private val binding: Binding,
        private val openDetails: (itemId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(nowPlayingMovie: NowPlayingMovie) {
            with(itemView){
                binding.textViewMovieTitleItem.text = nowPlayingMovie.title
                binding.textViewAverageVoteItem.text = nowPlayingMovie.voteAverage.toString()

                val posterAddress: String = AppConstants.posterBaseUrl + nowPlayingMovie.posterPath
                Glide.with(itemView.context)
                    .load(posterAddress)
                    .into(binding.imageViewPosterItem)
                setOnClickListener { openDetails(nowPlayingMovie.id) }
            }
        }
    }
}