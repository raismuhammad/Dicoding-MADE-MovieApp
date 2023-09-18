package com.raisproject.movieapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisproject.core.domain.usecase.MoviesUseCase

class MainViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies = moviesUseCase.getAllMovies().asLiveData()
}