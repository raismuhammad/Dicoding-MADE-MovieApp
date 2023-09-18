package com.raisproject.movieapp.di

import com.raisproject.core.domain.usecase.MoviesInteractor
import com.raisproject.core.domain.usecase.MoviesUseCase
import com.raisproject.movieapp.ui.detail.DetailViewModel
import com.raisproject.movieapp.ui.favorite.FavoriteViewModel
import com.raisproject.movieapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase>{ MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}