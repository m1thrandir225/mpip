package com.sebastijanzindl.labs3.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sebastijanzindl.labs3.R
import com.sebastijanzindl.labs3.databinding.FirstFragmentBinding
import com.sebastijanzindl.labs3.viewmodels.MovieDetailsViewModel
import com.sebastijanzindl.labs3.viewmodels.MoviesViewModel
import com.sebastijanzindl.labs3.viewmodels.MoviesViewModelFactory

class FirstFragment: Fragment(R.layout.first_fragment) {
    private var _binding: FirstFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesViewModel: MoviesViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {

        MoviesViewModelFactory(
            requireContext()
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FirstFragmentBinding.bind(view)
    }
}