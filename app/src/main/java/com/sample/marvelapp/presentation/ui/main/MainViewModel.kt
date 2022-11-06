package com.sample.marvelapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sample.marvelapp.domain.use_case.GetCharactersUseCase
import javax.inject.Inject

class MainViewModel@Inject constructor(getCharactersUseCase: GetCharactersUseCase): ViewModel() {

    val items = getCharactersUseCase().cachedIn(viewModelScope)
}