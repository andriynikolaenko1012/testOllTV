package com.test.testoll.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.testoll.R
import com.test.testoll.databinding.FragmentProfileBinding
import com.test.testoll.ext.disableBack

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var fragmentBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().disableBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding.apply {

        }
    }

}