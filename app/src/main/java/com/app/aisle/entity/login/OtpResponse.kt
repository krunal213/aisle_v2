package com.app.aisle.entity.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtpResponse(val token : String?) : Parcelable