package com.raisproject.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.raisproject.core.domain.model.Movie
import com.raisproject.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {



    fun getDetailMovie(id: Int) : LiveData<Movie>  {
        return moviesUseCase.getMovieById(id).asLiveData()
    }

    fun setFavoriteMovie(movie: Movie, state: Boolean) = moviesUseCase.setFavoriteMovies(movie, state)

}