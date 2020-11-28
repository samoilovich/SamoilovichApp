package com.samoilovich.courseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareViews()
    }

    private fun prepareViews() {
        findViewById<AppCompatButton>(R.id.start_movie_details_button)?.setOnClickListener {
            startActivity(Intent(this, MovieDetailsActivity::class.java))
        }
    }
}