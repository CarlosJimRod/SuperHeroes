package com.cjimenezro.superheroes.app.presentation.error

import com.cjimenezro.superheroes.R

class UnknownErrorUiModel(private val onClick: (() -> Unit)? = null) : ErrorUiModel {
    override fun getImage(): Int = R.drawable.error

    override fun getTitle(): Int = R.string.title_unknow_error

    override fun getDescription(): Int = R.string.description_unknow_error

    override fun onClickRetry(): (() -> Unit)? = onClick
}