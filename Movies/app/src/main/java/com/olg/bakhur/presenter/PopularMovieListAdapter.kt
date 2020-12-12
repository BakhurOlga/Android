package com.olg.bakhur.presenter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olg.bakhur.AppConstants
import com.olg.bakhur.R
import com.olg.bakhur.data.PopularMovies
import kotlinx.android.synthetic.main.item_popular_movie.view.*


class PopularMovieListAdapter(
    val popularMoviesList: MutableList<PopularMovies>,
    private val onItemMovieClickListener: OnItemMovieClickListener
) :
    RecyclerView.Adapter<PopularMovieListAdapter.PopularMovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieListViewHolder =
        PopularMovieListViewHolder(
            itemView = parent.run {
            LayoutInflater.from(context).inflate(R.layout.item_popular_movie, this, false)
        })

    override fun onBindViewHolder(holder: PopularMovieListViewHolder, position: Int) {
        holder.bind(popularMoviesList[position], onItemMovieClickListener)
    }

    override fun getItemCount(): Int = popularMoviesList.size

    fun updateMovieList(newMoviesList: MutableList<PopularMovies>) {
        popularMoviesList.apply {
            clear()
            addAll(newMoviesList)
        }
        notifyDataSetChanged() // он должен вызываться на адаптере
    }

    class PopularMovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(popularMovie: PopularMovies, onItemMovieClickListener: OnItemMovieClickListener) {
            with(popularMovie) {

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