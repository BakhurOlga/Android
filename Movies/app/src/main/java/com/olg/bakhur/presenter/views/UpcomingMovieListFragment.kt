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
import com.olg.bakhur.data.model.UpcomingMovie
import com.olg.bakhur.presenter.viewmodels.MovieViewModel
import com.olg.bakhur.presenter.interfaces.OnItemMovieClickListener
import com.olg.bakhur.presenter.adapters.UpcomingMovieListAdapter
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*
import kotlinx.android.synthetic.main.fragment_upcoming_movie_list.*

class UpcomingMovieListFragment : Fragment() { // по нажатии на кнопку back не переходит на предыдущий экран. Остается пустой экран активити

    private lateinit var movieViewModel: MovieViewModel
    private var movieList: MutableList<UpcomingMovie> = ArrayList()

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
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.upcomingMoviesList.observe(UpcomingMovieListFragment@this, Observer {  upcomingMovieList ->
            movieList = upcomingMovieList.upcomingMovieList
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val itemId = item.itemId
//        if (itemId == R.id.home){
//            findNavController().popBackStack()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}