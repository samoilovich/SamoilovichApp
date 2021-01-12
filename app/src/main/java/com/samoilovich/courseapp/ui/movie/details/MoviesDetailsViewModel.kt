package com.samoilovich.courseapp.ui.movie.details

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.samoilovich.courseapp.data.Actor
import kotlinx.coroutines.launch

class MoviesDetailsViewModel : ViewModel() {

    private val _actorsLiveData: MutableLiveData<MutableList<Actor>> by lazy {
        MutableLiveData<MutableList<Actor>>()
    }
    val actorsLiveData: LiveData<MutableList<Actor>> = _actorsLiveData
    private val gson = Gson()

    fun getMovieActors(assets: AssetManager?, actorIds: List<Int>?) {
        actorIds?.let {
            val reader =
                assets?.open("people.json", AssetManager.ACCESS_STREAMING)?.bufferedReader()
            viewModelScope.launch {
                val peopleStr = reader?.use { it.readText() }
                val actors = gson.fromJson(peopleStr, Array<Actor>::class.java).toMutableList()
                val movieActors = mutableListOf<Actor>()
                for (actorId in actorIds) {
                    val foundActors = actors.filter { actor -> actor.id == actorId }
                    movieActors.addAll(foundActors)
                }
                _actorsLiveData.value = movieActors
            }
        }
    }
}