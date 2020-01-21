package com.example.homework_1_activity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(val pictureName: Int, val description: String) : Parcelable {

    override fun toString(): String {
        return "Film $pictureName: $description"
    }
}