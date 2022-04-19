package com.sokolovds.domain.models

sealed class SettingsData(
    open val title: String
) {
    data class Header(
        override val title: String
    ) : SettingsData(title)

    sealed class SettingsDataWithChoose(
        override val title: String,
        open var isSelected: Boolean
    ) : SettingsData(title) {

        data class Language(
            override val title: String,
            override var isSelected: Boolean
        ) : SettingsDataWithChoose(title, isSelected)

        data class Themes(
            override val title: String,
            override var isSelected: Boolean
        ) : SettingsDataWithChoose(title, isSelected)
    }
}

typealias Header = SettingsData.Header
typealias Language = SettingsData.SettingsDataWithChoose.Language
typealias Themes = SettingsData.SettingsDataWithChoose.Themes
