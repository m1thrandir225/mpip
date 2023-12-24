package com.sebastijanzindl.lab2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.sebastijanzindl.lab2.R
import com.sebastijanzindl.lab2.adapters.MovieListAdapter
import com.sebastijanzindl.lab2.databinding.FragmentMovieListBinding
import com.sebastijanzindl.lab2.models.Movie
import com.sebastijanzindl.lab2.viewmodels.MovieDetailsViewModel

import com.sebastijanzindl.lab2.viewmodels.MovieListViewModel
import com.sebastijanzindl.lab2.viewmodels.MovieListViewModelFactory


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private var _binding: FragmentMovieListBinding? = null;
    private val binding get() = _binding!!;

    private  lateinit var movieListViewModel: MovieListViewModel;


    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {
        MovieListViewModelFactory(
            requireContext()
        )
    };

    private lateinit var goToDetails: Button;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view);

        val viewModelFactory = MovieListViewModelFactory(requireContext());

        val adapter = MovieListAdapter(ArrayList());

        movieListViewModel = ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java];

        goToDetails = view.findViewById(R.id.openNewMovieDialogButton);

        binding.movieListRecyclerView.adapter = adapter;

        movieListViewModel.getLiveDataMovies().observe(viewLifecycleOwner) {
            adapter.updateMovies(it);
        }

        adapter.setOnClickListener(object : MovieListAdapter.OnClickListener {
            override fun onClick(position: Int, model: Movie) {
                movieDetailsViewModel.listDetails(model.id.toString());
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, MovieDetailsFragment())
                    setReorderingAllowed(false)
                    addToBackStack(null);
                }
            }
        })

        movieListViewModel.listAllMovies()

        goToDetails.setOnClickListener {
           parentFragmentManager.commit {
               replace(R.id.fragment_container_view, AddNewMovieFragment())
               setReorderingAllowed(false);
               addToBackStack(null);
           }
        }
    }
}