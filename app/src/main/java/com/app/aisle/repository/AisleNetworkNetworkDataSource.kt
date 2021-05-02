package com.app.aisle.repository

import com.app.aisle.entity.login.PhoneNumberRequest
import com.app.aisle.networkcontract.AisleNetworkService
import javax.inject.Inject
import com.app.aisle.Result
import com.app.aisle.entity.home.Response
import com.app.aisle.entity.login.OtpRequest
import com.app.aisle.entity.login.OtpResponse
import com.app.aisle.entity.login.PhoneNumberResponse

class AisleNetworkNetworkDataSource @Inject constructor(private val aisleNetworkService: AisleNetworkService) : INetworkDataSource {

    override suspend fun validatePhoneNumber(phoneNumber: PhoneNumberRequest): Result<PhoneNumberResponse> {
        return try {
            Result.Success(aisleNetworkService.phone_number_login(phoneNumber).await())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    override suspend fun validateOTP(otpRequest: OtpRequest): Result<OtpResponse> {
        return try {
            Result.Success(aisleNetworkService.verify_otp(otpRequest).await())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    override suspend fun getProfileList(authorization: String): Result<Response> {
        return try {
            Result.Success(aisleNetworkService.test_profile_list(authorization).await())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}
