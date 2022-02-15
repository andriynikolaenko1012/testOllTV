package com.test.testoll.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.testoll.R
import com.test.testoll.databinding.FragmentFavouritesBinding
import com.test.testoll.ext.disableBack

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private lateinit var fragmentBinding: FragmentFavouritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().disableBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavouritesBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding.apply {

        }
    }
}