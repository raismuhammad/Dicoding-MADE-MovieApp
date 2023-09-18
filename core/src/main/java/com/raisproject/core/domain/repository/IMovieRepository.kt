package com.raisproject.core.domain.repository

import com.raisproject.core.data.Resource
import com.raisproject.core.data.source.local.entity.MovieEntity
import com.raisproject.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getMovieById(id: Int) : Flow<Movie>

}