package com.sokolovds.myapplication.presentation.models

import android.os.Parcelable
import com.sokolovds.domain.models.Note
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteParcelize(
    val id: Int = -1,
    val title: String,
    val description: String
) : Parcelable

fun Note.toNoteParcelize(): NoteParcelize {
    return NoteParcelize(
        id = this.id,
        title = this.title,
        description = this.description
    )
}