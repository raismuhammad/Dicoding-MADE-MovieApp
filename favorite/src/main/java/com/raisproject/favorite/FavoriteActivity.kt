package com.raisproject.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.raisproject.core.adapter.NowPlayingAdapter
import com.raisproject.favorite.databinding.ActivityFavoriteBinding
import com.raisproject.movieapp.ui.detail.DetailActivity
import com.raisproject.movieapp.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    lateinit var favAdapter: NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        favAdapter = NowPlayingAdapter()
        favAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
            startActivity(intent)
        }

        prepareRecyclerView()
        getData()

    }

    private fun getData() {
        viewModel.moviesFavorite.observe(this) {
            favAdapter.setData(it)
        }
    }

    private fun prepareRecyclerView() {
        binding.rvFavoriteMovies.setHasFixedSize(true)
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteMovies.adapter = favAdapter
    }
}