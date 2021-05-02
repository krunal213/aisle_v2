package com.app.aisle.entity.home


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val invites : Invites?,
	val likes : Likes?
) : Parcelable