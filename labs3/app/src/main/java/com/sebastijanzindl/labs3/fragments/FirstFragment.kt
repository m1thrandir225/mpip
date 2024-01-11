package com.sebastijanzindl.labs3.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.room.util.query
import com.sebastijanzindl.labs3.R
import com.sebastijanzindl.labs3.adapters.MoviesAdapter
import com.sebastijanzindl.labs3.databinding.FirstFragmentBinding
import com.sebastijanzindl.labs3.domain.Models.Movie
import com.sebastijanzindl.labs3.viewmodels.SecondFragmentViewModel
import com.sebastijanzindl.labs3.viewmodels.FirstFragmentViewModel
import com.sebastijanzindl.labs3.viewmodels.FirstFragmentViewModelFactory

class FirstFragment: Fragment(R.layout.first_fragment) {
    private var _binding: FirstFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var firstFragmentViewModel: FirstFragmentViewModel

    private val secondFragmentViewModel: SecondFragmentViewModel by activityViewModels {
        FirstFragmentViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FirstFragmentBinding.bind(view)

        val viewModeLfactory = FirstFragmentViewModelFactory(requireContext());
        firstFragmentViewModel = ViewModelProvider(this, viewModeLfactory)[FirstFragmentViewModel::class.java];

        binding.searchQuery.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchQuery = binding.searchQuery.text.toString();
                if(!searchQuery.isEmpty()) {
                    val keyboard = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                    firstFragmentViewModel.search(searchQuery);
                    keyboard?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

                }
            }
            true
        }

        val itemClick = object: MoviesAdapter.onClickListener {
            override fun onClickItem(id: String) {
                secondFragmentViewModel.listDetails(id)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, SecondFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        val adapter: MoviesAdapter = MoviesAdapter(ArrayList<Movie>(), itemClick)
        binding.movieRecyclerView.adapter = adapter

        firstFragmentViewModel.getLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        binding.submitQuery.setOnClickListener {
            val searchQuery = binding.searchQuery.text.toString()

            if(!searchQuery.isEmpty()) {
                firstFragmentViewModel.search(searchQuery)
            } else {
                println("HELLO WORLD");
            }
        }

        firstFragmentViewModel.listAll();

    }
}