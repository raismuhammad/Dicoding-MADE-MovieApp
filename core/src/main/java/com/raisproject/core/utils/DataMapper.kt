package com.raisproject.core.utils

import com.raisproject.core.data.source.local.entity.MovieEntity
import com.raisproject.core.data.source.remote.response.ResultMovies
import com.raisproject.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultMovies>): List<MovieEntity> {
        val moviesList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                id = it.id,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                release_date = it.release_date,
                overview = it.overview,
                popularity = it.popularity,
                vote_average = it.vote_average,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                release_date = it.release_date,
                overview = it.overview,
                popularity = it.popularity,
                vote_average = it.vote_average,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isFavorite = input.isFavorite
        )

    fun mapDetailEntityToDetailDomain(input: MovieEntity): Movie =
        Movie(
            id = input.id,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isFavorite = input.isFavorite
        )
}