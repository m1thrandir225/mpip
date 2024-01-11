package com.sebastijanzindl.labs3.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sebastijanzindl.labs3.R
import com.sebastijanzindl.labs3.databinding.SecondFragmentBinding
import com.sebastijanzindl.labs3.viewmodels.FirstFragmentViewModelFactory
import com.sebastijanzindl.labs3.viewmodels.SecondFragmentViewModel
import com.squareup.picasso.Picasso

class SecondFragment: Fragment(R.layout.second_fragment) {
    private var _binding: SecondFragmentBinding? = null;
    private val binding get() = _binding!!;

    private val secondFragmentModelView: SecondFragmentViewModel by activityViewModels {
        FirstFragmentViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SecondFragmentBinding.bind(view)

        secondFragmentModelView.getLiveData().observe(viewLifecycleOwner) {
            val movie = secondFragmentModelView.getLiveData().value

            if (movie != null) {
                Picasso.get().load(movie.poster).into(binding.moviePoster)
                binding.movieCountry.text = movie.country;
                binding.movieDirector.text = movie.director;
                binding.movieGenre.text = movie.genre;
                binding.movieId.text = movie.imdbID;
                binding.movieLanguage.text = movie.language;
                binding.movieRuntime.text = movie.runtime;
                binding.movieTitle.text = movie.title;
                binding.movieYear.text = movie.year;
                binding.moviePlot.text = movie.plot;
                binding.movieActors.text = movie.actors;
            }
        }
    }
}