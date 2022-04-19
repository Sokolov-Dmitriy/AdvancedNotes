package com.sokolovds.myapplication.presentation.screens.settingsFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.NoteFragmentBinding
import com.sokolovds.myapplication.databinding.SettingsFragmentBinding
import com.sokolovds.myapplication.presentation.base.BaseFragment
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.settings.SettingsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsFragmentBinding>() {

    private val viewModel by viewModel<SettingsFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.removeItem(R.id.actionSettings)
    }

    private fun initRecyclerView() {
        val adapter = SettingsAdapter(viewModel)
        viewModel.settingsData.observe(viewLifecycleOwner) {
            adapter.data = it
        }
        binding.root.adapter = adapter
        binding.root.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SettingsFragmentBinding.inflate(inflater, container, false)

}