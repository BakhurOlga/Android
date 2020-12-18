package com.olg.bakhur.presentation.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olg.bakhur.R
import com.olg.bakhur.application.App
import com.olg.bakhur.application.AppConstants
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.presentation.ui.common.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.details.MovieDetailsFragment
import com.olg.bakhur.presentation.ui.popular.adapter.PopularMovieListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*
import com.olg.bakhur.presentation.ui.viewModel

class PopularMovieListFragment : Fragment() {

    val viewModel by viewModel { App.component.popularMovieViewModel }
    private var movieList: MutableList<PopularMovie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_popular_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter(movieList)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPopularMovieList(AppConstants.apiKey)
            .observe(PopularMovieListFragment@ this, Observer { popularMovieList: List<PopularMovie> ->
                Log.d("TAG", popularMovieList.toString())
                movieList = popularMovieList as MutableList<PopularMovie>
                val adapter = recyclerPopularMovieList.adapter as PopularMovieListAdapter
                adapter.setData(movieList)
            })
    }

    private fun setUpAdapter(list: MutableList<PopularMovie>) {
        recyclerPopularMovieList.apply {
            adapter = PopularMovieListAdapter(object : OnItemMovieClickListener {
                override fun displayMovieDetails(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(MovieDetailsFragment.KEY_BUNDLE_MOVIE_ID, id)
                    findNavController().navigate(
                        R.id.action_popularMovieListFragment_to_movieDetailsFragment,
                        bundle
                    )
                }
            })
            (adapter as PopularMovieListAdapter).popularMoviesList = list
            layoutManager =
                LinearLayoutManager(PopularMovieListFragment@ this.context, RecyclerView.VERTICAL, false)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val itemId = item.itemId
//        if (itemId == R.id.home){
//            findNavController().popBackStack()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}