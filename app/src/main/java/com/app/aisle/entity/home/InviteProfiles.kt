package com.app.aisle.entity.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InviteProfiles (
    val general_information : General_information?,
    val photos : ArrayList<Photos>?
) : Parcelable