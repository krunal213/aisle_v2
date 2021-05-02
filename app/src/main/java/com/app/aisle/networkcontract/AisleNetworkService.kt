package com.app.aisle.networkcontract

import com.app.aisle.entity.home.Response
import com.app.aisle.entity.login.OtpRequest
import com.app.aisle.entity.login.OtpResponse
import com.app.aisle.entity.login.PhoneNumberResponse
import com.app.aisle.entity.login.PhoneNumberRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AisleNetworkService {

    @POST("users/phone_number_login")
    fun phone_number_login(@Body phoneNumberRequest: PhoneNumberRequest) : Deferred<PhoneNumberResponse>

    @POST("users/verify_otp")
    fun verify_otp(@Body otpRequest: OtpRequest) : Deferred<OtpResponse>

    @GET("users/test_profile_list")
    fun test_profile_list(@Header("Authorization")authorization : String) : Deferred<Response>



}