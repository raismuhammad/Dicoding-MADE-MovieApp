package com.raisproject.movieapp.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.raisproject.core.adapter.NowPlayingAdapter
import com.raisproject.core.data.Resource
import com.raisproject.movieapp.R
import com.raisproject.movieapp.databinding.ActivityMainBinding
import com.raisproject.movieapp.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var movieAdapter: NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieAdapter = NowPlayingAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
            startActivity(intent)
        }

        navigateOnToolbar()
        prepareRecyclerView()
        getData()
    }

    private fun navigateOnToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btn_fav -> {
                    val uri = Uri.parse("movieapp://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
                else -> false
            }
        }
    }

    private fun getData() {
        viewModel.movies.observe(this) {
            when (it) {
                is Resource.Success -> {
                    movieAdapter.setData(it.data)
                    Log.d("TAG", "ResultMovies: ${it.data}")
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Log.d("TAG", "errorMessage: ${it.message}")
                }
            }
        }
    }

    private fun prepareRecyclerView() {
        binding.rvNowPlaying.setHasFixedSize(true)
        binding.rvNowPlaying.layoutManager = LinearLayoutManager(this)
        binding.rvNowPlaying.adapter = movieAdapter
    }


}