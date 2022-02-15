package com.test.testoll.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.testoll.R
import com.test.testoll.databinding.FragmentSearchBinding
import com.test.testoll.ext.disableBack

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var fragmentBinding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().disableBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding.apply {

        }
    }

}