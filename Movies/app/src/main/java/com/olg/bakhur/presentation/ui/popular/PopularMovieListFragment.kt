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
import com.olg.bakhur.common.App
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.databinding.FragmentPopularMovieListBinding
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.presentation.ui.common.OnItemClickListener
import com.olg.bakhur.presentation.ui.popular.adapter.PopularMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel

class PopularMovieListFragment : Fragment() {

    private var bindingInst: FragmentPopularMovieListBinding? = null
    private val binding get() = bindingInst!!

    private val viewModel by viewModel { App.component.popularMovieViewModel }
    private var movieList: MutableList<PopularMovie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingInst = FragmentPopularMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInst = FragmentPopularMovieListBinding.bind(view)

        setupRecyclerView(movieList)
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInst = null
    }

    private fun setupRecyclerView(list: MutableList<PopularMovie>) {
        binding.recyclerPopularMovieList.apply {
            adapter = PopularMovieListAdapter(object : OnItemClickListener {
                override fun openDetails(id: Int) {
                    navigateToMovieDetailsFragment(id)
                }
            })
            (adapter as PopularMovieListAdapter).popularMoviesList = list
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun fetchData(){
        viewModel.getPopularMovieList(AppConstants.apiKey)
            .observe(viewLifecycleOwner, Observer { list: List<PopularMovie> ->
                movieList = list as MutableList<PopularMovie>
                val adapter = binding.recyclerPopularMovieList.adapter as PopularMovieListAdapter
                adapter.setData(movieList)
            })
    }

    fun navigateToMovieDetailsFragment(id: Int) {
        val action = PopularMovieListFragmentDirections
            .actionPopularMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}