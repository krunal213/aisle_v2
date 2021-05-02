package com.app.aisle.entity.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeDisplayContent(
    val inviteUserImage : String?,
    val inviteUserName : String?,
    val inviteUserAge : String?,
    val likes : ArrayList<LikesProfiles>?
) : Parcelable