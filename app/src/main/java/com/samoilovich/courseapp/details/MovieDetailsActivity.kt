package com.samoilovich.courseapp.details

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareViews()
    }

    private fun prepareViews() {
        prepareRating()
        prepareCast()
    }

    private fun prepareRating() {
        binding.movieRating.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RatingAdapter(4)
            setHasFixedSize(true)
        }
    }

    private fun prepareCast() {
        binding.movieActors.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = CastAdapter(getActors())
            addItemDecoration(getItemDecoration())
        }
    }

    private fun getActors(): List<Actor> {
        val actors = mutableListOf<Actor>()
        actors.add(Actor("Robert Downey Jr.", R.drawable.avatar_robert))
        actors.add(Actor("Chris Evans", R.drawable.avatar_chris))
        actors.add(Actor("Mark Ruffalo", R.drawable.avatar_mark))
        actors.add(Actor("Chris Hemsworth", R.drawable.avatar_chris_thor))
        return actors
    }

    private fun getItemDecoration(): DividerItemDecoration {
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.divider)
        val decorator = DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL)
        drawable?.let {
            decorator.setDrawable(drawable)
        }
        return decorator
    }
}