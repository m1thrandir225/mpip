package com.example.labratoryexercise

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ImplicitActivity : AppCompatActivity() {
    private  lateinit var  textView: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        textView = findViewById(R.id.implicitTextView);

        textView.text = getActivities().joinToString("\n") {
            it.activityInfo.name;
        }
    }


    private fun getActivities(): List<ResolveInfo> {

        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);


        return packageManager.queryIntentActivities(intent, 0);
    }
}