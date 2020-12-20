package com.olg.bakhur.presentation.ui.popular

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
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.popular.adapter.PopularMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*

class PopularMovieListFragment : Fragment() {

    private val viewModel by viewModel { App.component.popularMovieViewModel }
    private var movieList: MutableList<PopularMovie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_popular_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(movieList)
        fetchData()
    }

    private fun setupRecyclerView(list: MutableList<PopularMovie>) {
        recyclerPopularMovieList.apply {
            adapter = PopularMovieListAdapter(object : OnItemMovieClickListener {
                override fun openDetails(id: Int) {
                    navigateToMovieDetailsFragment(id)
                }
            })
            (adapter as PopularMovieListAdapter).popularMoviesList = list
            layoutManager =
                LinearLayoutManager(PopularMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }

    private fun fetchData(){
        viewModel.getPopularMovieList(AppConstants.apiKey)
            .observe(PopularMovieListFragment@ this, Observer { list: List<PopularMovie> ->
                movieList = list as MutableList<PopularMovie>
                val adapter = recyclerPopularMovieList.adapter as PopularMovieListAdapter
                adapter.setData(movieList)
            })
    }

    fun navigateToMovieDetailsFragment(id: Int) {
        val action = PopularMovieListFragmentDirections
            .actionPopularMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}