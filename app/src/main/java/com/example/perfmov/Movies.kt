package com.example.perfmov

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
        val id: Int,
        val title: String,
        val poster_path: String,
        val vote_average: String,
        val release_date: String
) : Parcelable