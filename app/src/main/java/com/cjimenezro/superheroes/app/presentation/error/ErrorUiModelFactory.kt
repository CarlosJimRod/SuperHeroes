package com.cjimenezro.superheroes.app.presentation.error

import com.cjimenezro.superheroes.app.domain.ErrorApp

class ErrorUiModelFactory {
    fun create(errorApp: ErrorApp, onClick: (() -> Unit)? = null): ErrorUiModel {
        return when (errorApp) {
            ErrorApp.DataError -> DataErrorUiModel(onClick)
            ErrorApp.InternetError -> InternetErrorUiModel(onClick)
            ErrorApp.ServerError -> ServerErrorUiModel(onClick)
            ErrorApp.UnknownError -> UnknownErrorUiModel(onClick)
        }
    }
}