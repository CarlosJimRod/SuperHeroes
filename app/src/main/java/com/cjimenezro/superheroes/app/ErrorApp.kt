package com.cjimenezro.superheroes.app

sealed class ErrorApp {
    object UnknowError: ErrorApp()
}