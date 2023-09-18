package com.raisproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.raisproject.core.data.source.local.entity.MovieEntity
import com.raisproject.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun getMovieById(id: Int) : Flow<MovieEntity> = movieDao.getMovieById(id)

    fun insertMovies(moviesList: List<MovieEntity>) = movieDao.insertMovies(moviesList)

    fun setFavoriteMovies(movies: MovieEntity, newState: Boolean) {
        movies.isFavorite = newState
        movieDao.updateFavoriteMovie(movies)
    }
}