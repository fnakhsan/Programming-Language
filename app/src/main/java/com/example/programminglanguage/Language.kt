package com.example.programminglanguage

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(
    var name: String? = "",
    var paradigm: String? = "",
    var developer: String? = "",
    var detail: String? = "",
    var photo: Int? = 0
) : Parcelable
