package com.sokolovds.myapplication.presentation.models

import com.sokolovds.domain.models.Note



fun Note.toNoteParcelize(): NoteParcelize {
    return NoteParcelize(
        id = this.id,
        title = this.title,
        description = this.description
    )
}
