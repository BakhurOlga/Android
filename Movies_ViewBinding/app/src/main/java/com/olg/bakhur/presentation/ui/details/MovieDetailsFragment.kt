package com.olg.bakhur.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.olg.bakhur.common.App
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.databinding.FragmentMovieDetailsBinding
import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.presentation.ui.viewModel

class MovieDetailsFragment : Fragment() {

    private var bindingInst: FragmentMovieDetailsBinding? = null
    private val binding get() = bindingInst!!

    private val viewModel by viewModel { App.component.movieDetailsViewModel }
    private val args: MovieDetailsFragmentArgs by navArgs()
    private var movieId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingInst = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInst = FragmentMovieDetailsBinding.bind(view)

        movieId = args.movieId
        movieId?.let {
            viewModel.getMovieDetails(it, AppConstants.apiKey)
                .observe(viewLifecycleOwner, Observer { movieDetails: MovieDetails ->
                    with(movieDetails) {
                        binding.textViewMovieTitle.text = title
                        binding.textViewAverageVote.text = voteAverage.toString()
                        binding.textViewOverview.text = overview
                        binding.textViewMovieBudget.text = budget.toString()
                        binding.textViewMovieOriginalLang.text = originalLanguage
                        binding.textViewMoviePopularity.text = popularity.toString()
                        binding.textViewMovieReleaseDate.text = releaseDate

                        val posterAddress: String = AppConstants.posterBaseUrl + posterPath

                        Glide.with(this@MovieDetailsFragment)
                            .load(posterAddress)
                            .into(binding.imageViewPoster)
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInst = null
    }
}