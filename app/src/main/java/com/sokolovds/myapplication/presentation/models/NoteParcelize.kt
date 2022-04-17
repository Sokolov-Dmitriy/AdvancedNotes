package com.sokolovds.myapplication.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteParcelize(
    val id: Int = -1,
    val title: String,
    val description: String
) : Parcelable
