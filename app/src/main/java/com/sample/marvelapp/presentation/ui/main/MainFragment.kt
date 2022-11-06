package com.sample.marvelapp.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.marvelapp.R
import com.sample.marvelapp.databinding.FragmentMainBinding
import com.sample.marvelapp.presentation.ui.detail.DetailFragment
import com.sample.marvelapp.presentation.ui.main.adapter.character.CharacterPagingAdapter
import com.sample.marvelapp.presentation.ui.main.adapter.state.CharacterLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharacterPagingAdapter {
        navigateToDetailScreen(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        lifecycleScope.launch {
            mainViewModel.items.collectLatest { adapter.submitData(it) }
        }
    }

    private fun initViews() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = this@MainFragment.adapter.withLoadStateFooter(CharacterLoadStateAdapter { this@MainFragment.adapter.retry() })
            }

            adapter.addLoadStateListener {
                progressListLoading.isVisible = it.source.refresh is LoadState.Loading
                recyclerView.isVisible = it.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = it.source.refresh is LoadState.Error
            }

            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        Bundle().apply {
            putInt(DetailFragment.BUNDLE_KEY_CHARACTER_ID, id)
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}