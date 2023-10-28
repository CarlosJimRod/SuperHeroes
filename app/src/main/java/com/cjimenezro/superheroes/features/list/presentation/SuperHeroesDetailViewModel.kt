package com.cjimenezro.superheroes.features.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeByIdUseCase
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeUseCase
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroesDetailViewModel(private val getSuperHeroeByIdUseCase: GetSuperHeroeByIdUseCase)
    : ViewModel() {

    private val _uiState= MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHeroe(id:String){
        _uiState.value= UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroeByIdUseCase(id).fold(
                { responseError(it) },
                { responseGetSuperHeroeSuccess(it) })
        }
    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    private fun responseGetSuperHeroeSuccess(superHeroe: SuperHeroe){
        _uiState.postValue(UiState(superHeroe=superHeroe, isLoading = false))
    }


    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val superHeroe: SuperHeroe? = null
    )
}