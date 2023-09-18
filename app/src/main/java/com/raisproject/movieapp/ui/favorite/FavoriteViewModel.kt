package com.raisproject.movieapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisproject.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val moviesFavorite = moviesUseCase.getFavoritemovies().asLiveData()
}