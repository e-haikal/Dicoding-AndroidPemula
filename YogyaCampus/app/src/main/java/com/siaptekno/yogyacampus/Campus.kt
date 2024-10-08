package com.siaptekno.yogyacampus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Campus(
    val name: String,
    val description: String,
    val photo: String,
    val countryRank: String,
    val worldRank: String
) : Parcelable
