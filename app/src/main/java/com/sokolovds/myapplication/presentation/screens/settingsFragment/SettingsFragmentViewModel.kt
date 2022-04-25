package com.sokolovds.myapplication.presentation.screens.settingsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokolovds.domain.models.Header
import com.sokolovds.domain.models.Language
import com.sokolovds.domain.models.SettingsData
import com.sokolovds.domain.models.Themes
import com.sokolovds.myapplication.presentation.base.BaseViewModel
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.settings.SettingsViewHolder

class SettingsFragmentViewModel : BaseViewModel(), SettingsViewHolder.SwitchListener {
    private val _settingsData: MutableLiveData<List<SettingsData>> =
        MutableLiveData<List<SettingsData>>()
    val settingsData: LiveData<List<SettingsData>> = _settingsData

    private val startData = mutableListOf<SettingsData>(
        Header("Choose the language:"),
        Language("English", true),
        Language("Russian", false),
        Header("Choose the theme:"),
        Themes("Dark", false),
        Themes("Light", true)
    )

    init {
        _settingsData.postValue(startData)
    }

    data class SelectedSetting<T>(
        val position: Int
    )

    override fun onChangeLanguage(position: Int) {
        changeData(SelectedSetting<Language>(position))
    }


    override fun onChangeTheme(position: Int) {
        changeData(SelectedSetting<Themes>(position))
    }


    private inline fun <reified T : SettingsData.SettingsDataWithChoose> changeData(settings: SelectedSetting<T>) {
        startData.map {
            if (it is T) {
                it.isSelected = false
            }
            (startData[settings.position] as T).isSelected = true
        }
        _settingsData.postValue(startData)
    }


}