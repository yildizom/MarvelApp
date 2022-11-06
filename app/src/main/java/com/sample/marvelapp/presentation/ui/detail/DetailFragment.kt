package com.sample.marvelapp.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sample.marvelapp.R
import com.sample.marvelapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment: Fragment() {

    @Inject
    lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

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
        initObservers()
    }

    private fun getData() {
        arguments?.getInt(BUNDLE_KEY_CHARACTER_ID)?.let {
            viewModel.retrieveCharacterById(it)
        } ?: findNavController().navigateUp()
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