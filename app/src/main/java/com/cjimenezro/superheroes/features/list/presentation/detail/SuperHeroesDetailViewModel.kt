package com.cjimenezro.superheroes.features.list.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.presentation.error.ErrorUiModel
import com.cjimenezro.superheroes.app.presentation.error.toErrorUi
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeByIdUseCase
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroesDetailViewModel @Inject constructor(
    private val getSuperHeroeByIdUseCase: GetSuperHeroeByIdUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHeroe(id: String) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroeByIdUseCase(id).fold(
                { responseError(it, id) },
                { responseGetSuperHeroeSuccess(it) })
        }
    }

    private fun responseError(errorApp: ErrorApp, id: String) {
        _uiState.postValue(UiState(errorApp = errorApp.toErrorUi {
            loadSuperHeroe(id)
        }))
    }

    private fun responseGetSuperHeroeSuccess(superHeroe: SuperHeroe) {
        _uiState.postValue(UiState(superHeroe = superHeroe))
    }


    data class UiState(
        val errorApp: ErrorUiModel? = null,
        val isLoading: Boolean = false,
        val superHeroe: SuperHeroe? = null
    )
}