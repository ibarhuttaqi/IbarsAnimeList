package com.example.ibarsanimelist.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibarsanimelist.R
import com.example.ibarsanimelist.core.data.Resource
import com.example.ibarsanimelist.databinding.FragmentHomeBinding
import com.example.ibarsanimelist.core.ui.AnimeAdapter
import com.example.ibarsanimelist.core.utils.DataMapper
import com.example.ibarsanimelist.ui.detail.DetailAnimeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animeAdapter = AnimeAdapter()
        animeAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailAnimeActivity::class.java)
            intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.anime.observe(viewLifecycleOwner) { anime ->
            Log.d("HomeFragment", "anime: $anime")
            if (anime != null) {
                when (anime) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val uiModelList = anime.data?.map { DataMapper.mapDomainToUI(it) }
                        animeAdapter.setData(uiModelList)
                    }

                    is Resource.Error -> {
                        binding?.apply {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                anime.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }

        with(binding.rvAnime) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}