package com.example.labratoryexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExplicitActivity : AppCompatActivity() {

    private lateinit var textInput: EditText;

    private lateinit var submitButton: Button;

    private lateinit var cancelButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        textInput = findViewById(R.id.textInput);

        submitButton = findViewById(R.id.getInputButton);

        cancelButton = findViewById(R.id.cancelActivityButton);


        submitButton.setOnClickListener {
            Intent().let {
                it.putExtra("result", textInput.text.toString());
                setResult(RESULT_OK, it)
                finish();
            }
        }

        cancelButton.setOnClickListener {
            Intent().let {
                setResult(RESULT_CANCELED, it);
                finish();
            }
        }

    }
}