package com.sebastijanzindl.lab2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.sebastijanzindl.lab2.R
import com.sebastijanzindl.lab2.databinding.FragmentMovieDetailsBinding
import com.sebastijanzindl.lab2.databinding.FragmentMovieListBinding
import com.sebastijanzindl.lab2.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private var _binding: FragmentMovieDetailsBinding? = null;

    private val binding get() = _binding!!;

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels();


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view);

        val details = movieDetailsViewModel.getDetails().observe(viewLifecycleOwner) {
            binding.movieTitle.text = it.title;
            binding.movieActors.text = it.actors.joinToString(", ");
            binding.movieDescription.text = it.description;
            binding.movieDirector.text = it.director;
        }
    }
}