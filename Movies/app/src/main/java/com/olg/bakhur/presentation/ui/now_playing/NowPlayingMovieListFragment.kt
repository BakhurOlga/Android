package com.olg.bakhur.presentation.ui.now_playing

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
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.now_playing.adapter.NowPlayingMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel
import kotlinx.android.synthetic.main.fragment_now_playing_movie_list.*

class NowPlayingMovieListFragment : Fragment() {

    private val viewModel by viewModel { App.component.nowPlayingMovieViewModel }
    private var movieList: MutableList<NowPlayingMovie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_now_playing_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(movieList)
        fetchData()
    }

    private fun setupRecyclerView(list: MutableList<NowPlayingMovie>) {
        recyclerNowPlayingMovieList.apply {
            adapter = NowPlayingMovieListAdapter(object : OnItemMovieClickListener {
                override fun openDetails(id: Int) {
                    navigateToMovieDetailsFragment(id)
                }
            })
            (adapter as NowPlayingMovieListAdapter).nowPlayingMoviesList = list
            layoutManager =
                LinearLayoutManager(LatestMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }

    private fun fetchData(){
        viewModel.getNowPlayingMovieList(AppConstants.apiKey)
            .observe(
                NowPlayingMovieListFragment@ this, Observer { list: List<NowPlayingMovie> ->
                    movieList = list as MutableList<NowPlayingMovie>
                    val adapter = recyclerNowPlayingMovieList.adapter as NowPlayingMovieListAdapter
                    adapter.setData(movieList)
                })
    }

    fun navigateToMovieDetailsFragment(id: Int) {
        val action = NowPlayingMovieListFragmentDirections
            .actionNowPlayingMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}