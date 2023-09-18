package com.raisproject.core.domain.usecase

import com.raisproject.core.data.Resource
import com.raisproject.core.domain.model.Movie
import com.raisproject.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val movieRepository: IMovieRepository) : MoviesUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getFavoritemovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovies(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getMovieById(id: Int): Flow<Movie> = movieRepository.getMovieById(id)
}