package com.app.aisle.entity.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos (
	val photo : String?,
) : Parcelable