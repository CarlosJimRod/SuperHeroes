package com.cjimenezro.superheroes.app.data.remote

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.left
import com.cjimenezro.superheroes.app.domain.right
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Either<ErrorApp, T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (ex: Throwable) {
        return when (ex) {
            is ConnectException -> ErrorApp.InternetError.left()
            is UnknownHostException -> ErrorApp.InternetError.left()
            is SocketTimeoutException -> ErrorApp.ServerError.left()
            else -> ErrorApp.UnknownError.left()
        }
    }
    if (!response.isSuccessful) {
        return ErrorApp.UnknownError.left()
    }
    return response.body()!!.right()
}