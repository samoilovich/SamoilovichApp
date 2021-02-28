package com.samoilovich.courseapp.repo.converter

import com.samoilovich.courseapp.data.Genre
import com.samoilovich.courseapp.repo.model.GenresResponse

object GenreConverter {

    fun genresResponseToGenreList(genresResponse: GenresResponse): HashMap<Int, Genre> {
        val genres = hashMapOf<Int, Genre>()
        genresResponse.genres?.let { genreItemsResponse ->
            for (item in genreItemsResponse) {
                item?.let {
                    val id = item.id ?: -1
                    val genre = Genre(
                        id = id,
                        name = item.name ?: ""
                    )
                    genres[id] = genre
                }
            }
        }
        return genres
    }
}