package com.olg.bakhur.presenter.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.R
import com.olg.bakhur.presenter.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
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

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieId?.let {
            movieViewModel.getMovieDetails(it).observe(MovieDetailsFragment@ this, Observer { movie ->

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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val itemId = item.itemId
//        if (itemId == R.id.home){
//            findNavController().popBackStack()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    companion object{
        const val KEY_BUNDLE_MOVIE_ID = "KEY_BUNDLE_MOVIE_ID"
        val movieDetailsFragment = MovieDetailsFragment()
    }
}