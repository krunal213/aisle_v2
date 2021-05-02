package com.app.aisle.discover.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.aisle.R
import com.app.aisle.Result
import com.app.aisle.entity.home.HomeDisplayContent
import com.app.aisle.extension.error
import com.app.aisle.extension.ifTrueThen
import com.app.aisle.repository.AisleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val applicationObj: Application,
    private val aisleRepository: AisleRepository
) : AndroidViewModel(applicationObj) {

    fun getAuthorization(): LiveData<Result<String>> {
        val authorizationLiveData = MutableLiveData<Result<String>>()
        viewModelScope.launch(Dispatchers.IO) {
            authorizationLiveData.postValue(
                aisleRepository.getAuthorization()
            )
        }
        return authorizationLiveData
    }

    fun getProfileList(authorization: String?): LiveData<Result<HomeDisplayContent>> {
        val profileListLiveData = MutableLiveData<Result<HomeDisplayContent>>()
        viewModelScope.launch(Dispatchers.IO) {
            authorization?.isNotEmpty()
                ?.takeIf { it }
                ?.ifTrueThen {
                    profileListLiveData.postValue(Result.InProgress)
                    viewModelScope.launch(Dispatchers.IO) {
                        when (val result = authorization?.let { aisleRepository.getProfileList(it) }) {
                            is Result.Success -> {
                                profileListLiveData.postValue(
                                    result.t.invites
                                        ?.profiles
                                        ?.isNotEmpty()
                                        ?.takeIf { it }
                                        ?.let {
                                            with(result.t.invites.profiles[0]){
                                                Result.Success(
                                                    HomeDisplayContent(
                                                        photos?.get(0)?.photo,
                                                        general_information?.first_name,
                                                        general_information?.age.toString(),
                                                        result.t.likes?.profiles
                                                    )
                                                )
                                            }
                                        }
                                )
                            }
                            else ->{
                                profileListLiveData.postValue(
                                    error(
                                        R.string.error_something_went_wrong
                                    )
                                )
                            }
                        }
                    }

                }
        }
        return profileListLiveData
    }

}