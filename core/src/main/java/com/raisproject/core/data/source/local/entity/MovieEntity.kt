package com.raisproject.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    var id: Int,
    var title: String? = null,
    var original_title: String? = null,
    var poster_path: String? = null,
    var backdrop_path: String? = null,
    var release_date: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var vote_average: Double? = null,
    var isFavorite: Boolean = false
    ) : Parcelable