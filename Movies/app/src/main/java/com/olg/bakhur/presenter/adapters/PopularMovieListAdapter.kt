package com.olg.bakhur.presenter.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olg.bakhur.R
import com.olg.bakhur.data.model.PopularMovies
import com.olg.bakhur.presenter.interfaces.OnItemMovieClickListener
import kotlinx.android.synthetic.main.item_popular_movie.view.*


class PopularMovieListAdapter(private val onItemMovieClickListener: OnItemMovieClickListener) :
    RecyclerView.Adapter<PopularMovieListAdapter.PopularMovieListViewHolder>() {

    var popularMoviesList: MutableList<PopularMovies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieListViewHolder =
        PopularMovieListViewHolder(
            itemView = parent.run {
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie, parent, false)
        })

    override fun onBindViewHolder(holder: PopularMovieListViewHolder, position: Int) {
        holder.bind(popularMoviesList[position], onItemMovieClickListener)
    }

    override fun getItemCount(): Int = popularMoviesList.size

    fun setData(newMoviesList: MutableList<PopularMovies>) {
        popularMoviesList.apply {
            clear()
            addAll(newMoviesList)
        }
        notifyDataSetChanged()
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