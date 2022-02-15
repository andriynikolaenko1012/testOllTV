package com.test.testoll.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.testoll.R
import com.test.testoll.databinding.FragmentMainBinding
import com.test.testoll.ext.disableBack

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var fragmentBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().disableBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding.apply {

            teleButton.setOnClickListener {
                findNavController().navigate(R.id.televisionFragment)
            }

        }
    }

}