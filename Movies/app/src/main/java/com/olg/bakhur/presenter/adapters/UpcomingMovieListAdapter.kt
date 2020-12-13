package com.olg.bakhur.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.R
import com.olg.bakhur.data.server_pojo.UpcomingMovie
import com.olg.bakhur.presenter.interfaces.OnItemMovieClickListener
import kotlinx.android.synthetic.main.item_now_playing_movie.view.*

class UpcomingMovieListAdapter(val upcomingMoviesList: MutableList<UpcomingMovie>,
                               private val onItemMovieClickListener: OnItemMovieClickListener
) :
    RecyclerView.Adapter<UpcomingMovieListAdapter.UpcomingMovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieListViewHolder =
        UpcomingMovieListViewHolder(itemView = parent.run {
            LayoutInflater.from(context).inflate(R.layout.item_upcoming_movie, this, false)
        })

    override fun onBindViewHolder(holder: UpcomingMovieListViewHolder, position: Int) {
        holder.bind(upcomingMoviesList[position], onItemMovieClickListener)
    }

    override fun getItemCount(): Int = upcomingMoviesList.size

    fun updateMovieList(newUpcomingMoviesList: MutableList<UpcomingMovie>) {
        upcomingMoviesList.apply {
            clear()
            addAll(newUpcomingMoviesList)
        }
        notifyDataSetChanged()
    }

    class UpcomingMovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(upcomingMovie: UpcomingMovie, onItemMovieClickListener: OnItemMovieClickListener) {
            with(upcomingMovie) {

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