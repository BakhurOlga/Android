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
import com.olg.bakhur.common.App
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.databinding.FragmentUpcomingMovieListBinding
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.presentation.ui.upcoming.adapter.UpcomingMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel

class UpcomingMovieListFragment : Fragment() {

    private var bindingInst: FragmentUpcomingMovieListBinding? = null
    private val binding get() = bindingInst!!

    private val viewModel by viewModel { App.component.upcomingMovieViewModel }
    private var movieList: MutableList<UpcomingMovie> = ArrayList()
    private val rvAdapter by lazy { UpcomingMovieListAdapter(this@UpcomingMovieListFragment::openDetails) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingInst = FragmentUpcomingMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInst = FragmentUpcomingMovieListBinding.bind(view)

        setupRecyclerView(movieList)
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInst = null
    }

    private fun setupRecyclerView(list: MutableList<UpcomingMovie>) {
        binding.recyclerUpcomingMovieList.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        rvAdapter.upcomingMoviesList = list
    }

    private fun fetchData(){
        viewModel.getUpcomingMoviesList(AppConstants.apiKey)
            .observe(viewLifecycleOwner, Observer { list: List<UpcomingMovie> ->
                movieList = list as MutableList<UpcomingMovie>
                rvAdapter.setData(movieList)
            })
    }

    private fun openDetails(id: Int) {
        val action = UpcomingMovieListFragmentDirections
            .actionUpcomingMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}