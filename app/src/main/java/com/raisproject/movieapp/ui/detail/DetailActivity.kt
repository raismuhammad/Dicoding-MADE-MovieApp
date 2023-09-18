package com.raisproject.movieapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.raisproject.core.domain.model.Movie
import com.raisproject.core.utils.Constant
import com.raisproject.movieapp.R
import com.raisproject.movieapp.databinding.ActivityDetailBinding
import convertDate
import loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private var id = 0

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundleData()
        getMovieData(id)
        backButton()

    }

    private fun getBundleData() {
        id = intent.getIntExtra(EXTRA_DATA, 0)
        Log.d("TAG", "getBundleData: $id")
    }

    private fun getMovieData(id: Int) {
        Log.d("TAG", "getMovieData: $id")
        viewModel.getDetailMovie(id).observe(this) { data ->
            showMovieData(data)
            Log.d("TAG", "getMovieData: $data")
            binding.btnFav.setOnClickListener { setFavorite(data) }
        }

    }

    private fun setFavorite(data: Movie) {
        var likeState = data.isFavorite
        likeState = !likeState
        setStatusFavorite(likeState)
        viewModel.setFavoriteMovie(data, likeState)
    }

    private fun showMovieData(data: Movie) {
        val releaseDate = data.release_date?.convertDate(
            Constant.YYYY_MM_DD_FORMAT,
            Constant.EEE_D_MMM_YYYY_FORMAT
        )
        binding.ivPoster.loadImage(data.poster_path)
        binding.ivBackdrop.loadImage(data.backdrop_path)
        binding.tvTitle.text = data.title
        binding.tvVoteAverage.text = data.vote_average.toString()
        binding.tvOriginalTitle.text = data.original_title
        binding.tvReleaseDate.text = releaseDate
        binding.tvPopularity.text = data.popularity.toString()
        binding.tvOverview.text = data.overview
        var statusFavorite: Boolean = data.isFavorite
        setStatusFavorite(statusFavorite)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red))
        } else {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }
    }

    private fun backButton() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}