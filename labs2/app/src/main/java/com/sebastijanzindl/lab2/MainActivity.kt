package com.sebastijanzindl.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.sebastijanzindl.lab2.fakeapi.FakeAPI
import com.sebastijanzindl.lab2.fakeapi.FakeAPIProvider
import com.sebastijanzindl.lab2.fragments.MovieListFragment
import com.sebastijanzindl.lab2.repository.MovieRepository

class MainActivity : AppCompatActivity() {
    private lateinit var repository: MovieRepository;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true);

            }
        }
        this.repository = MovieRepository(FakeAPIProvider.getInstance());
    }
}