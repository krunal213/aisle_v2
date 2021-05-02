package com.app.aisle

sealed class Result<out R> {
    data class Success<out T>(val t : T) : Result<T>()
    data class Error(val ex : Exception) : Result<Nothing>()
    object InProgress : Result<Nothing>()
}