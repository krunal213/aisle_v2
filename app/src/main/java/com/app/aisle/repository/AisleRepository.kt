package com.app.aisle.repository

import com.app.aisle.Result
import com.app.aisle.entity.home.Response
import com.app.aisle.entity.login.OtpRequest
import com.app.aisle.entity.login.OtpResponse
import com.app.aisle.entity.login.PhoneNumberResponse
import com.app.aisle.entity.login.PhoneNumberRequest
import javax.inject.Inject

class AisleRepository @Inject constructor(
    private val aisleNetworkDataSource : AisleNetworkNetworkDataSource,
    private val aisleLocalDataSource: AisleLocalDataSource
) : IRepository {

    override suspend fun validatePhoneNumber(phoneNumber: PhoneNumberRequest) : Result<PhoneNumberResponse> {
        return aisleNetworkDataSource.validatePhoneNumber(phoneNumber)
    }

    override suspend fun validateOTP(otpRequest: OtpRequest): Result<OtpResponse> {
        return aisleNetworkDataSource.validateOTP(otpRequest)
    }

    override suspend fun saveToken(phoneNumber: String?, token: String?) {
        aisleLocalDataSource.saveToken(phoneNumber,token)
    }

    override suspend fun isUserLogin(): Result<Boolean> {
        return aisleLocalDataSource.isUserLogin()
    }

    override suspend fun getAuthorization(): Result<String> {
        return aisleLocalDataSource.getAuthorization()
    }

    override suspend fun getProfileList(authorization: String): Result<Response> {
        return aisleNetworkDataSource.getProfileList(authorization)
    }


}