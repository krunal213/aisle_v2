package com.app.aisle.repository

import com.app.aisle.Result
import com.app.aisle.entity.home.Response
import com.app.aisle.entity.login.OtpRequest
import com.app.aisle.entity.login.OtpResponse
import com.app.aisle.entity.login.PhoneNumberResponse
import com.app.aisle.entity.login.PhoneNumberRequest

interface IRepository {

    suspend fun validatePhoneNumber(phoneNumber: PhoneNumberRequest): Result<PhoneNumberResponse>

    suspend fun validateOTP(otpRequest: OtpRequest) : Result<OtpResponse>

    suspend fun saveToken(phoneNumber: String?, token: String?)

    suspend fun isUserLogin() : Result<Boolean>

    suspend fun getAuthorization() : Result<String>

    suspend fun getProfileList(authorization: String) : Result<Response>
}
