package com.app.aisle.repository

import com.app.aisle.Result

interface ILocalDataSource {

    suspend fun saveToken(phoneNumber: String?, token: String?) : Boolean

    suspend fun isUserLogin() : Result<Boolean>

    suspend fun getAuthorization(): Result<String>

}