package com.test.testoll.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testoll.R
import com.test.testoll.data.ProgramViewModel
import com.test.testoll.databinding.FragmentTelevisionBinding
import com.test.testoll.ext.androidUUID
import com.test.testoll.ui.activity.DetailActivity
import com.test.testoll.ui.adapter.ListLoadStateAdapter
import com.test.testoll.ui.adapter.ProgramAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class TelevisionFragment : Fragment(R.layout.fragment_television) {

    private lateinit var fragmentBinding: FragmentTelevisionBinding

    private lateinit var adapterProgram: ProgramAdapter

    private val programViewModel : ProgramViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { findNavController().navigateUp() }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTelevisionBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding.apply {

            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            adapterProgram = ProgramAdapter{ item, imgView ->
                startActivity(Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra("channel_icon", item.icon)
                    putExtra("channel_name", item.channelName)
                    putExtra("program_name", item.name)
                    putExtra("program_description", item.description)
                })
            }

            adapterProgram.apply {

                addLoadStateListener { loadState ->
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            rvItems.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }

                        else -> {
                            rvItems.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }

            val adapterWithLoading = adapterProgram.withLoadStateHeaderAndFooter(
                ListLoadStateAdapter(adapterProgram::retry),
                ListLoadStateAdapter(adapterProgram::retry)
            )

            rvItems.apply {
                layoutManager = LinearLayoutManager(requireContext())
                hasFixedSize()
                adapter = adapterWithLoading
            }

            observeDada()
        }
    }

    private fun observeDada(){
        lifecycleScope.launch {
            programViewModel.getProgramListPaged(requireContext().androidUUID()).collectLatest {  pagingData ->
                adapterProgram.submitData(pagingData)
            }
        }
    }
}