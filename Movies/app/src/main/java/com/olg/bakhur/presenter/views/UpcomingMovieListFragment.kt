package com.olg.bakhur.presenter.views

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
import com.olg.bakhur.data.server_pojo.UpcomingMovie
import com.olg.bakhur.domain.viewmodels.MovieViewModel
import com.olg.bakhur.presenter.interfaces.OnItemMovieClickListener
import com.olg.bakhur.presenter.adapters.UpcomingMovieListAdapter
import kotlinx.android.synthetic.main.fragment_upcoming_movie_list.*

class UpcomingMovieListFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private var movieList: MutableList<UpcomingMovie> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_upcoming_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.upcomingMoviesList.observe(UpcomingMovieListFragment@this, Observer {  upcomingMovieList ->
            movieList = upcomingMovieList.upcomingMovieList

            initRecyclerViewAdapter(movieList)
        })
    }

    private fun initRecyclerViewAdapter(list: MutableList<UpcomingMovie>) {
        recyclerUpcomingMovieList.apply {
            adapter = UpcomingMovieListAdapter(list, object : OnItemMovieClickListener {
                override fun displayMovieDetails(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(MovieDetailsFragment.KEY_BUNDLE_MOVIE_ID, id)
                    findNavController().navigate(R.id.action_upcomingMovieListFragment_to_movieDetailsFragment, bundle)
                }
            })
            layoutManager =
                LinearLayoutManager(UpcomingMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }
}