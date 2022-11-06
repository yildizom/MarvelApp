package com.sample.marvelapp.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sample.marvelapp.R
import com.sample.marvelapp.databinding.FragmentDetailBinding
import com.sample.marvelapp.presentation.ui.detail.adapter.ComicPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment: Fragment() {

    @Inject
    lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val adapter = ComicPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initViews()
        initObservers()
    }

    private fun getData() {
        arguments?.getInt(BUNDLE_KEY_CHARACTER_ID)?.also {
            viewModel.retrieveCharacterById(it)

            lifecycleScope.launch {
                viewModel.comicItems(it).collectLatest { adapter.submitData(it) }
            }
        } ?: findNavController().navigateUp()
    }

    private fun initViews() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.adapter
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.image.collectLatest {
                Glide.with(requireContext()).load(it).into(binding.ivCharacterImage)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val BUNDLE_KEY_CHARACTER_ID = "detail-character-id"
    }
}