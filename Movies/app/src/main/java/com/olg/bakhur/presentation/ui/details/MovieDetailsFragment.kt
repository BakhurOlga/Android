package com.olg.bakhur.presentation.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.R
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

    override val viewModel by viewModel { App.component.movieDetailsViewModel }

    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt(KEY_BUNDLE_MOVIE_ID)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        movieId?.let {
            viewModel.getMovieDetails(it).observe(MovieDetailsFragment@ this, Observer { movie ->

                Log.d("TAG", movie.toString())
                with(movie) {
                    textViewMovieTitle.text = title
                    textViewAverageVote.text = voteAverage.toString()
                    textViewOverview.text = overview
                    textViewMovieBudget.text = budget.toString()
                    textViewMovieOriginalLang.text = originalLanguage
                    textViewMoviePopularity.text = popularity.toString()
                    textViewMovieReleaseDate.text = releaseDate

                    val posterAddress: String = AppConstants.posterBaseUrl + posterPath

                    Glide.with(this@MovieDetailsFragment)
                        .load(posterAddress)
                        .into(imageViewPoster)
                }
            })
        }
    }

    companion object{
        const val KEY_BUNDLE_MOVIE_ID = "KEY_BUNDLE_MOVIE_ID"
        val movieDetailsFragment = MovieDetailsFragment()
    }
}