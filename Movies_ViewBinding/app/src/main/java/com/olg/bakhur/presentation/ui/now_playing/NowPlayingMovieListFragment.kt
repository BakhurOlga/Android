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
import com.olg.bakhur.common.App
import com.olg.bakhur.common.AppConstants
import com.olg.bakhur.databinding.FragmentNowPlayingMovieListBinding
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.presentation.ui.now_playing.adapter.NowPlayingMovieListAdapter
import com.olg.bakhur.presentation.ui.viewModel

class NowPlayingMovieListFragment : Fragment() {

    private var bindingInst: FragmentNowPlayingMovieListBinding? = null
    private val binding get() = bindingInst!!

    private val viewModel by viewModel { App.component.nowPlayingMovieViewModel }
    private var movieList: MutableList<NowPlayingMovie> = ArrayList()
    private val rvAdapter by lazy { NowPlayingMovieListAdapter(this@NowPlayingMovieListFragment::openDetails) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingInst = FragmentNowPlayingMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInst = FragmentNowPlayingMovieListBinding.bind(view)

        setupRecyclerView(movieList)
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInst = null
    }

    private fun setupRecyclerView(list: MutableList<NowPlayingMovie>) {
        binding.recyclerNowPlayingMovieList.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        rvAdapter.nowPlayingMoviesList = list
    }

    private fun fetchData(){
        viewModel.getNowPlayingMovieList(AppConstants.apiKey)
            .observe(
                viewLifecycleOwner, Observer { list: List<NowPlayingMovie> ->
                    movieList = list as MutableList<NowPlayingMovie>
                    rvAdapter.setData(movieList)
                })
    }

    private fun openDetails(id: Int) {
        val action = NowPlayingMovieListFragmentDirections
            .actionNowPlayingMovieListFragmentToMovieDetailsFragment(id)

        findNavController().navigate(action)
    }
}