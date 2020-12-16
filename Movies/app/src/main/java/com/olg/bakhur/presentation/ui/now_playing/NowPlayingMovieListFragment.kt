package com.olg.bakhur.presentation.ui.now_playing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olg.bakhur.R
import com.olg.bakhur.data.model.NowPlayingMovies
import com.olg.bakhur.presentation.OnItemMovieClickListener
import com.olg.bakhur.presentation.ui.details.MovieDetailsViewModel
import com.olg.bakhur.presentation.ui.now_playing.adapter.NowPlayingMovieListAdapter
import com.olg.bakhur.presentation.ui.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.fragment_now_playing_movie_list.*
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*

class NowPlayingMovieListFragment : Fragment() { // по нажатии на кнопку back не переходит на предыдущий экран. Остается пустой экран активити

    private lateinit var movieViewModel: MovieDetailsViewModel
    private var movieList: MutableList<NowPlayingMovies> = ArrayList()

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
        movieViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        movieViewModel.nowPlayingMovieList.observe(NowPlayingMovieListFragment@this, Observer { nowPlayingMovieList ->
            movieList = nowPlayingMovieList.nowPlayingMovieList
            val adapter = recyclerNowPlayingMovieList.adapter as NowPlayingMovieListAdapter
            adapter.setData(movieList)
        })
    }

    private fun setUpAdapter(list: MutableList<NowPlayingMovies>) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.home){
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}