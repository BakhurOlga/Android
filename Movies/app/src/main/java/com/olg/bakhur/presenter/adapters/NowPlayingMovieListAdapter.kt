package com.olg.bakhur.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.R
import com.olg.bakhur.data.server_pojo.NowPlayingMovies
import com.olg.bakhur.presenter.interfaces.OnItemMovieClickListener
import kotlinx.android.synthetic.main.item_now_playing_movie.view.*

class NowPlayingMovieListAdapter(private val onItemMovieClickListener: OnItemMovieClickListener) :
    RecyclerView.Adapter<NowPlayingMovieListAdapter.NowPlayingMovieListViewHolder>() {

    var nowPlayingMoviesList: MutableList<NowPlayingMovies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieListViewHolder =
        NowPlayingMovieListViewHolder(itemView = parent.run {
            LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing_movie, parent, false)
        })

    override fun onBindViewHolder(holder: NowPlayingMovieListViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position], onItemMovieClickListener)
    }

    override fun getItemCount(): Int = nowPlayingMoviesList.size

    fun setData(newNowPlayingMovies: MutableList<NowPlayingMovies>) {
        nowPlayingMoviesList.apply {
            clear()
            addAll(newNowPlayingMovies)
        }
        notifyDataSetChanged()
    }

    class NowPlayingMovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(nowPlayingMovie: NowPlayingMovies, onItemMovieClickListener: OnItemMovieClickListener) {
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