package com.sample.marvelapp.presentation.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.marvelapp.domain.model.Character
import com.sample.marvelapp.domain.model.Res
import com.sample.marvelapp.domain.use_case.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase):
    ViewModel() {

    val loadingOF = ObservableField(true)
    val characterOF = ObservableField<Character>()
    val errorOF = ObservableField<String?>()
    private val _image = MutableSharedFlow<String>()
    val image: SharedFlow<String> = _image

    fun retrieveCharacterById(id: Int) {
        getCharacterUseCase(id).onEach {
            when (it) {
                is Res.Success -> onSuccess(it.data)
                is Res.Loading -> onLoading()
                is Res.Error -> onError(it.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun onSuccess(character: Character) {
        viewModelScope.launch {
            _image.emit(character.thumbnail)
        }
        characterOF.set(character)
        loadingOF.set(false)
        errorOF.set(null)
    }

    private fun onLoading() {
        loadingOF.set(true)
        errorOF.set(null)
    }

    private fun onError(message: String) {
        errorOF.set(message)
        loadingOF.set(false)
    }
}