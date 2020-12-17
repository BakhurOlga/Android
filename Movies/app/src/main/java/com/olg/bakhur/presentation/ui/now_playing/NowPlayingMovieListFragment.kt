package com.olg.bakhur.presentation.ui.now_playing

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
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.details.MovieDetailsFragment
import com.olg.bakhur.presentation.ui.details.MovieDetailsViewModel
import com.olg.bakhur.presentation.ui.now_playing.adapter.NowPlayingMovieListAdapter
import kotlinx.android.synthetic.main.fragment_now_playing_movie_list.*
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*

class NowPlayingMovieListFragment : Fragment() {

    var viewModel by viewModel { App.component.nowPlayingMovieViewModel }
    private var movieList: MutableList<NowPlayingMovie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_now_playing_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter(movieList)
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.getNowPlayingMovieList()
            .observe(NowPlayingMovieListFragment@ this, Observer { nowPlayingMovieList: List<NowPlayingMovie> ->
                movieList = nowPlayingMovieList as MutableList<NowPlayingMovie>
                val adapter = recyclerNowPlayingMovieList.adapter as NowPlayingMovieListAdapter
                adapter.setData(movieList)
            })
    }

    private fun setUpAdapter(list: MutableList<NowPlayingMovie>) {
        recyclerNowPlayingMovieList.apply {
            adapter = NowPlayingMovieListAdapter(object : OnItemMovieClickListener {
                override fun displayMovieDetails(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(MovieDetailsFragment.KEY_BUNDLE_MOVIE_ID, id)
                    findNavController().navigate(
                        R.id.action_nowPlayingMovieListFragment_to_movieDetailsFragment,
                        bundle
                    )
                }
            })
            (adapter as NowPlayingMovieListAdapter).nowPlayingMoviesList = list
            layoutManager =
                LinearLayoutManager(LatestMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }
}