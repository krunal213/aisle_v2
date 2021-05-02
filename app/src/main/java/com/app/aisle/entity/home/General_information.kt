package com.app.aisle.entity.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class General_information (
	val first_name : String?,
	val age : Int?
) : Parcelable