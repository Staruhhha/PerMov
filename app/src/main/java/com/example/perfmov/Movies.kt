package com.example.perfmov

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies (
    val id : Int,
    val title : String,
    val poster : String,
    val rating : String,
    val date : String
) : Parcelable