package com.app.aisle.repository

import android.app.Application
import android.content.SharedPreferences
import com.app.aisle.R
import com.app.aisle.Result
import com.app.aisle.extension.ifElse
import javax.inject.Inject
import kotlin.Exception

class AisleLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val application: Application
) : ILocalDataSource {

    override suspend fun saveToken(phoneNumber: String?, token: String?): Boolean {
        return with(application) {
            return try {
                sharedPreferences
                    .edit()
                    .putString(getString(R.string.pref_phone_number), phoneNumber)
                    .putString(getString(R.string.pref_token), token)
                    .commit()
            } catch (ex: Exception) {
                false
            }
        }
    }

    override suspend fun isUserLogin(): Result<Boolean> {
        return with(application) {
             return try {
                sharedPreferences
                    .getString(getString(R.string.pref_token), "")
                    ?.isNotEmpty()
                    .ifElse({ Result.Success(true) },
                        { Result.Error(Exception()) })
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }
    }

    override suspend fun getAuthorization(): Result<String> {
        return with(application) {
            return try {
                sharedPreferences
                    .getString(getString(R.string.pref_token), "")
                    .ifElse({ Result.Success(it) },
                        { Result.Error(Exception()) })
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }
    }


}


