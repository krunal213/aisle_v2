package com.app.aisle.entity.home

import android.os.Parcelable
import com.app.aisle.entity.home.InviteProfiles
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Invites (
	val profiles : ArrayList<InviteProfiles>?,
) : Parcelable