package com.app.aisle.entity.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LikesProfiles(
    val first_name	: String?,
    val avatar	: String?
) : Parcelable
