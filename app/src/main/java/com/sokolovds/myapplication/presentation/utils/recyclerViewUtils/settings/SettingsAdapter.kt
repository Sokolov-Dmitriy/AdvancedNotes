package com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.domain.models.Header
import com.sokolovds.domain.models.Language
import com.sokolovds.domain.models.SettingsData
import com.sokolovds.domain.models.Themes
import com.sokolovds.myapplication.databinding.SettingsHeaderBinding
import com.sokolovds.myapplication.databinding.SettingsItemBinding

class SettingsAdapter(private val listener: SettingsViewHolder.SwitchListener) :
    RecyclerView.Adapter<SettingsViewHolder>() {

    var data: List<SettingsData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            TYPE_HEADER -> SettingsHeaderBinding.inflate(inflater, parent, false)
            TYPE_LANGUAGE -> SettingsItemBinding.inflate(inflater, parent, false)
            TYPE_THEMES -> SettingsItemBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Invalid type")
        }

        return SettingsViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Header -> 0
            is Language -> 1
            is Themes -> 2
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_LANGUAGE = 1
        private const val TYPE_THEMES = 2
    }


}