package com.cjimenezro.superheroes.app.presentation.error

import com.cjimenezro.superheroes.app.domain.ErrorApp

fun ErrorApp.toErrorUi(onClickRetry: (() -> Unit)? = null): ErrorUiModel {
    return ErrorUiModelFactory().create(this, onClickRetry)
}