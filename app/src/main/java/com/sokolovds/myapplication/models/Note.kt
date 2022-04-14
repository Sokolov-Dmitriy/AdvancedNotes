package com.sokolovds.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Note(
    val id:Int = -1,
    val title: String,
    val description: String
):Parcelable
