package com.olg.bakhur.presentation.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olg.bakhur.R
import com.olg.bakhur.common.App
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.upcoming.adapter.UpcomingMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*
import kotlinx.android.synthetic.main.fragment_upcoming_movie_list.*

class UpcomingMovieListFragment : Fragment() {

    private val viewModel by viewModel { App.component.upcomingMovieViewModel }
    private var movieList: MutableList<UpcomingMovie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_upcoming_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewAdapter(movieList)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUpcomingMoviesList(AppConstants.apiKey)
            .observe(UpcomingMovieListFragment@ this, Observer { list: List<UpcomingMovie> ->
                movieList = list as MutableList<UpcomingMovie>
                val adapter = recyclerUpcomingMovieList.adapter as UpcomingMovieListAdapter
                adapter.setData(movieList)
            })
    }

    private fun initRecyclerViewAdapter(list: MutableList<UpcomingMovie>) {
        recyclerUpcomingMovieList.apply {
            adapter = UpcomingMovieListAdapter(object : OnItemMovieClickListener {
                override fun displayMovieDetails(id: Int) {
                    navigateToMovieDetailsFragment(id)
                }
            })
            (adapter as UpcomingMovieListAdapter).upcomingMoviesList = list
            layoutManager =
                LinearLayoutManager(UpcomingMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }

    fun navigateToMovieDetailsFragment(id: Int) {
        val action = UpcomingMovieListFragmentDirections
            .actionUpcomingMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}