package com.example.ibarsanimelist.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.ibarsanimelist.R
import com.example.ibarsanimelist.databinding.ActivityDetailAnimeBinding
import com.example.ibarsanimelist.core.ui.AnimeUIModel
import com.example.ibarsanimelist.core.utils.DataMapper
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAnimeActivity : AppCompatActivity() {

    private val detailAnimeViewModel: DetailAnimeViewModel by viewModel()
    private lateinit var binding: ActivityDetailAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        val detailAnime = intent.getParcelableExtra<AnimeUIModel>(EXTRA_DATA)
        detailAnime?.let {
            // Ambil data terbaru dari database menggunakan ViewModel berdasarkan ID anime
            lifecycleScope.launch {
                detailAnimeViewModel.getAnimeById(it.id).collect { animeFromDb ->
                    animeFromDb?.let { anime ->
                        // Map the domain model (Anime) to UI model (AnimeUIModel)
                        val mappedAnimeUIModel = DataMapper.mapDomainToUI(anime)
                        showDetailAnime(mappedAnimeUIModel) // Pass the mapped UI model
                    }
                }
            }
        }
    }

    private fun showDetailAnime(detailAnime: AnimeUIModel) {
        detailAnime.let {
            supportActionBar?.title = detailAnime.title
            binding.content.tvDetailDescription.text = detailAnime.synopsis
            Glide.with(this@DetailAnimeActivity)
                .load(detailAnime.imageUrl)
                .into(binding.ivDetailImage)

            var statusFavorite = detailAnime.isFavorite
            Log.d("DetailAnimeActivity", "Status Favorite: $statusFavorite")
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                val mappedDetailAnimeUIModel = DataMapper.mapUIToDomain(detailAnime)
                statusFavorite = !statusFavorite
                detailAnimeViewModel.setFavoriteAnime(mappedDetailAnimeUIModel, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_menu_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}