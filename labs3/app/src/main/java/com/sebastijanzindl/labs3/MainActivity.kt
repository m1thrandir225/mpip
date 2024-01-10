package com.sebastijanzindl.labs3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.sebastijanzindl.labs3.fragments.FirstFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container, FirstFragment())
                setReorderingAllowed(true);
            }
        }
    }
}