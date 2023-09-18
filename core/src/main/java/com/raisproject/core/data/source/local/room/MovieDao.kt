package com.raisproject.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raisproject.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_table WHERE id=:id")
    fun getMovieById(id: Int): Flow<MovieEntity>
}