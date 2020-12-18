package com.olg.bakhur.presentation.ui.now_playing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.R
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import kotlinx.android.synthetic.main.item_now_playing_movie.view.*

class NowPlayingMovieListAdapter(
    private val onItemMovieClickListener: OnItemMovieClickListener
) : RecyclerView.Adapter<NowPlayingMovieListAdapter.NowPlayingMovieListViewHolder>() {

    var nowPlayingMoviesList: MutableList<NowPlayingMovie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieListViewHolder =
        NowPlayingMovieListViewHolder(itemView = parent.run {
            LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing_movie, parent, false)
        })

    override fun onBindViewHolder(holder: NowPlayingMovieListViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position], onItemMovieClickListener)
    }

    override fun getItemCount(): Int = nowPlayingMoviesList.size

    fun setData(newNowPlayingMovies: MutableList<NowPlayingMovie>) {
        nowPlayingMoviesList.apply {
            clear()
            addAll(newNowPlayingMovies)
        }
        notifyDataSetChanged()
    }

    class NowPlayingMovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(nowPlayingMovie: NowPlayingMovie, onItemMovieClickListener: OnItemMovieClickListener) {
            with(nowPlayingMovie) {

                itemView.apply {
                    textViewMovieTitleItem.text = title
                    textViewAverageVoteItem.text = voteAverage.toString()
                }

                itemView.setOnClickListener { onItemMovieClickListener.displayMovieDetails(id) }

                val posterAddress: String = AppConstants.posterBaseUrl + posterPath

                Glide.with(itemView.context)
                    .load(posterAddress)
                    .into(itemView.imageViewPosterItem)
            }
        }
    }
}