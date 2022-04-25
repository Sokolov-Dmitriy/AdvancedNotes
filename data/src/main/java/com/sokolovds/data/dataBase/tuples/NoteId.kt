package com.sokolovds.data.dataBase.tuples

import androidx.room.ColumnInfo


data class NoteId(
    @ColumnInfo(name = "id") val id: Int
)