package com.olg.bakhur.presentation.ui.upcoming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olg.bakhur.R
import com.olg.bakhur.application.App
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.details.MovieDetailsViewModel
import com.olg.bakhur.presentation.ui.upcoming.adapter.UpcomingMovieListAdapter
import com.olg.bakhur.presentation.ui.details.MovieDetailsFragment
import com.olg.bakhur.presentation.ui.now_playing.NowPlayingMovieViewModel
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*
import kotlinx.android.synthetic.main.fragment_upcoming_movie_list.*
import javax.inject.Inject

class UpcomingMovieListFragment : Fragment() {

    @Inject
    internal lateinit var upcomingMovieViewModel: UpcomingMovieViewModel
    private var movieList: MutableList<UpcomingMovie> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_upcoming_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter(movieList)
    }

    override fun onResume() {
        super.onResume()
        upcomingMovieViewModel.getUpcomingMoviesList(AppConstants.apiKey).observe(UpcomingMovieListFragment@this, Observer { upcomingMovieList: List<UpcomingMovie> ->
            movieList = upcomingMovieList  as MutableList<UpcomingMovie>
            val adapter = recyclerUpcomingMovieList.adapter as UpcomingMovieListAdapter
            adapter.setData(movieList)
        })
    }

    private fun setUpAdapter(list: MutableList<UpcomingMovie>) {
        recyclerUpcomingMovieList.apply {
            adapter = UpcomingMovieListAdapter(object : OnItemMovieClickListener {
                override fun displayMovieDetails(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(MovieDetailsFragment.KEY_BUNDLE_MOVIE_ID, id)
                    findNavController().navigate(R.id.action_upcomingMovieListFragment_to_movieDetailsFragment, bundle)
                }
            })
            (adapter as UpcomingMovieListAdapter).upcomingMoviesList = list
            layoutManager =
                LinearLayoutManager(UpcomingMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }
}