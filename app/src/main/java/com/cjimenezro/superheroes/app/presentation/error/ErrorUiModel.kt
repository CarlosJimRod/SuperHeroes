package com.cjimenezro.superheroes.app.presentation.error

interface ErrorUiModel {
    fun getImage(): Int
    fun getTitle(): Int
    fun getDescription(): Int
    fun onClickRetry(): (() -> Unit)?
}