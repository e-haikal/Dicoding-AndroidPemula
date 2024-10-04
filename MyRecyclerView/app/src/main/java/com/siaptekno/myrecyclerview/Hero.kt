package com.siaptekno.myrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
