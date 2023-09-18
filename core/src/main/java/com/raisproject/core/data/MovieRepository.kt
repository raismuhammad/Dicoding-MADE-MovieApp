package com.raisproject.core.data

import com.raisproject.core.data.source.local.LocalDataSource
import com.raisproject.core.data.source.remote.RemoteDataSource
import com.raisproject.core.data.source.remote.network.ApiResponse
import com.raisproject.core.data.source.remote.response.ResultMovies
import com.raisproject.core.domain.model.Movie
import com.raisproject.core.domain.repository.IMovieRepository
import com.raisproject.core.utils.AppExecutors
import com.raisproject.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<ResultMovies>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultMovies>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<ResultMovies>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asFlow()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }

    override fun getMovieById(id: Int): Flow<Movie> {
        return localDataSource.getMovieById(id).map {
            DataMapper.mapDetailEntityToDetailDomain(it)
        }
    }
}