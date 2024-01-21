package com.cjimenezro.superheroes.features.list.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeUseCase
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroesListViewModel @Inject constructor(private val getSuperHeroeUseCase: GetSuperHeroeUseCase)
    :ViewModel() {

    private val _uiState= MutableLiveData<UiState>()
    val uiState:LiveData<UiState> = _uiState

    fun loadSuperHeroe(){
        _uiState.value= UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroeUseCase().fold(
                { responseError(it) },
                { responseGetSuperHeroeSuccess(it) })
        }
    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    private fun responseGetSuperHeroeSuccess(superHeroe: List<SuperHeroe>){
        _uiState.postValue(UiState(superHeroe=superHeroe, isLoading = false))
    }


    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val superHeroe: List<SuperHeroe>? = null
    )
}