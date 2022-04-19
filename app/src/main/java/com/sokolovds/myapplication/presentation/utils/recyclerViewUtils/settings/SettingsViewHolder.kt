package com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.settings

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sokolovds.domain.models.Header
import com.sokolovds.domain.models.Language
import com.sokolovds.domain.models.SettingsData
import com.sokolovds.domain.models.Themes
import com.sokolovds.myapplication.databinding.SettingsHeaderBinding
import com.sokolovds.myapplication.databinding.SettingsItemBinding

class SettingsViewHolder(private val binding: ViewBinding, private val listener: SwitchListener) :
    RecyclerView.ViewHolder(binding.root) {

    interface SwitchListener {
        fun onChangeLanguage(position: Int)
        fun onChangeTheme(position: Int)
    }

    private fun bindHeader(data: Header) {
        val root = binding as SettingsHeaderBinding
        if (adapterPosition == 0) {
            root.line.visibility = View.GONE
        }
        root.headerTitle.text = data.title
    }

    private fun bindChooseHolder(data: SettingsData.SettingsDataWithChoose) {
        val binding = binding as SettingsItemBinding
        with(binding) {
            title.text = data.title
            if (data.isSelected) {
                switchBtn.isChecked = true
                switchBtn.isEnabled = false
            } else {
                switchBtn.isChecked = false
                switchBtn.isEnabled = true
                switchBtn.setOnClickListener {
                    when (data) {
                        is Language -> listener.onChangeLanguage(adapterPosition)
                        is Themes -> listener.onChangeTheme(adapterPosition)
                    }
                }
            }

        }
    }

    private fun bindLanguage(data: Language) {
        bindChooseHolder(data)

    }

    private fun bindThemes(data: Themes) {
        bindChooseHolder(data)
    }

    fun bind(data: SettingsData) {
        when (data) {
            is Header -> bindHeader(data)
            is Language -> bindLanguage(data)
            is Themes -> bindThemes(data)
        }
    }
}