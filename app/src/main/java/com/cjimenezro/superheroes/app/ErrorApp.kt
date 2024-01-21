package com.cjimenezro.superheroes.app

sealed class ErrorApp {
    object UnknownError : ErrorApp()
    object DataError : ErrorApp()
    object InternetError : ErrorApp()
    object ServerError : ErrorApp()
}