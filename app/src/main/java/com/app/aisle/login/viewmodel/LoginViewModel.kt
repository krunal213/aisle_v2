package com.app.aisle.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.aisle.R
import com.app.aisle.Result
import com.app.aisle.entity.login.OtpRequest
import com.app.aisle.entity.login.OtpResponse
import com.app.aisle.entity.login.PhoneNumberResponse
import com.app.aisle.entity.login.PhoneNumberRequest
import com.app.aisle.extension.error
import com.app.aisle.extension.ifTrueThen
import com.app.aisle.extension.orElse
import com.app.aisle.repository.AisleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val applicationObj: Application,
    private val aisleRepository: AisleRepository
) : AndroidViewModel(applicationObj) {

    fun validatePhoneNumber(phoneNumber: String): LiveData<Result<PhoneNumberResponse>> {
        val phoneNumberLiveData = MutableLiveData<Result<PhoneNumberResponse>>()
        viewModelScope.launch(Dispatchers.IO) {
            phoneNumber
                .let { it.trim().isNotEmpty() && it.trim().length == 13 }
                .takeIf { it }
                ?.apply {
                    phoneNumberLiveData.postValue(Result.InProgress)
                    when (val result = aisleRepository.validatePhoneNumber(PhoneNumberRequest(phoneNumber))) {
                        is Result.Success -> {
                            result?.t?.status
                                ?.ifTrueThen {
                                    phoneNumberLiveData.postValue(
                                        result
                                    )
                                }
                                ?.orElse {
                                    phoneNumberLiveData.postValue(
                                        error(
                                            R.string.error_something_went_wrong
                                        )
                                    )
                                }
                        }
                        else -> {
                            phoneNumberLiveData.postValue(
                                result
                            )
                        }
                    }
                }
        }
        return phoneNumberLiveData
    }

    fun validateOtp(phoneNumber: String, otp: String): LiveData<Result<OtpResponse>> {
        val otpLiveData = MutableLiveData<Result<OtpResponse>>()
        viewModelScope.launch(Dispatchers.IO) {
            otp
                .let { it.trim().isNotEmpty() && it.trim().length == 4 }
                .takeIf { it }
                ?.apply {
                    otpLiveData.postValue(Result.InProgress)
                    when (val result = aisleRepository.validateOTP(
                        OtpRequest(
                            phoneNumber,
                            otp
                        )
                    )) {
                        is Result.Success -> {
                            result.t.token
                                ?.let { it.isNotEmpty() }
                                ?.ifTrueThen {
                                    viewModelScope.launch(Dispatchers.IO){
                                        aisleRepository.saveToken(phoneNumber, result.t.token)
                                        otpLiveData.postValue(
                                            result
                                        )
                                    }
                                }
                                ?.orElse {
                                    otpLiveData.postValue(
                                        error(
                                            R.string.error_something_went_wrong
                                        )
                                    )
                                }
                        }
                        else -> {
                            otpLiveData.postValue(
                                result
                            )
                        }
                    }
                }
        }
        return otpLiveData
    }

    fun isUserLogin() : LiveData<Result<Boolean>> {
        val userLiveData = MutableLiveData<Result<Boolean>>()
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000L)
            userLiveData.postValue(
                aisleRepository.isUserLogin()
            )
        }
        return userLiveData
    }


}