package com.sebastijanzindl.lab2.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.sebastijanzindl.lab2.R
import com.sebastijanzindl.lab2.databinding.FragmentAddNewMovieBinding
import com.sebastijanzindl.lab2.fakeapi.FakeAPIProvider
import com.sebastijanzindl.lab2.models.Movie
import com.sebastijanzindl.lab2.repository.MovieRepository
import java.util.UUID


class AddNewMovieFragment : Fragment(R.layout.fragment_add_new_movie) {
    private lateinit var repository: MovieRepository;

    private var _binding: FragmentAddNewMovieBinding? = null;
    private val binding get() = _binding!!;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = MovieRepository(FakeAPIProvider.getInstance());

        _binding = FragmentAddNewMovieBinding.bind(view);

        binding.newMovieButton.setOnClickListener {
            val title = binding.movieTitle.text.toString();
            val director = binding.movieDirector.text.toString();
            val description = binding.movieDescription.text.toString();
            val actors = binding.movieActors.text.toString().split(",");

            repository.addNewMovie(title, description, director, actors);

            parentFragmentManager.commit {
                replace(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(false);


            }
        }

    }
}