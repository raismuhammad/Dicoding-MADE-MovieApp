package com.raisproject.core.domain.usecase

import com.raisproject.core.data.Resource
import com.raisproject.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies() : Flow<Resource<List<Movie>>>
    fun getFavoritemovies() : Flow<List<Movie>>
    fun setFavoriteMovies(movie : Movie, state: Boolean)
    fun getMovieById(id: Int) : Flow<Movie>
}