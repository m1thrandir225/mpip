package com.example.labratoryexercise

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView;

    private lateinit var explicitActivityButton: Button;

    private lateinit var implicitActivityButton: Button;

    private lateinit var choosePictureButton: Button;

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        when(result.resultCode) {
            RESULT_OK -> {
                val intent = result.data;
                if(intent?.hasExtra("result") == true) {
                    textView.text = intent.getStringExtra("result");
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        explicitActivityButton = findViewById(R.id.explicitActivityButton);

        implicitActivityButton = findViewById(R.id.implicitActivityButton);

        choosePictureButton = findViewById(R.id.pictureChoiceButton);

        textView = findViewById(R.id.explicitResultTextView);

        explicitActivityButton.setOnClickListener {
            navigateToExplicitActivity()
        }

        implicitActivityButton.setOnClickListener {
            navigateToImplicitActivity();
        }

        choosePictureButton.setOnClickListener {
            openImagePicker();
        }
    }

    private fun navigateToExplicitActivity() {
        Intent(applicationContext, ExplicitActivity::class.java).let { intent ->
            intent.putExtra("result", "wow");
            getContent.launch(intent);
        }
    }

    private fun navigateToImplicitActivity() {
        Intent(Intent.ACTION_SEND).also {
            it.action = "mk.ukim.finki.lab_intents.IMPLICIT_ACTION"
            it.type = "text/plain"

            startActivity(it)
        }
    }

   private fun openImagePicker() {
       Intent(Intent.ACTION_PICK).also {
           it.type = "image/*"
           getContent.launch(it)
       }
   }


}