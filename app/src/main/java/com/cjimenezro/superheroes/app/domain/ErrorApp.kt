package com.cjimenezro.superheroes.app.domain

sealed class ErrorApp {
    object UnknownError : ErrorApp()
    object DataError : ErrorApp()
    object InternetError : ErrorApp()
    object ServerError : ErrorApp()
}