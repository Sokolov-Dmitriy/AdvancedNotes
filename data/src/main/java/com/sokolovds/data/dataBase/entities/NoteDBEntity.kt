package com.sokolovds.data.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sokolovds.domain.models.Note

@Entity(tableName = "notes")
data class NoteDBEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
) {
    fun toNote() = Note(
        id = id,
        title = title,
        description = description
    )
}

