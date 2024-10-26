package com.example.ibarsanimelist.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibarsanimelist.core.ui.AnimeAdapter
import com.example.ibarsanimelist.core.utils.DataMapper
import com.example.ibarsanimelist.di.FavoriteModule
import com.example.ibarsanimelist.favorite.databinding.FragmentFavoriteBinding
import com.example.ibarsanimelist.ui.detail.DetailAnimeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

//    private val favoriteViewModel by viewModels<HomeViewModel> {
//        ViewModelFactory.getInstance(requireContext())
//    }
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loadKoinModules(FavoriteModule)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val animeAdapter = AnimeAdapter()
            animeAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailAnimeActivity::class.java)
                intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

//            val factory = ViewModelFactory.getInstance(requireActivity())
//            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteAnime.observe(viewLifecycleOwner) { dataAnime ->
                val uiModelList = dataAnime.map { DataMapper.mapDomainToUI(it) }
                animeAdapter.setData(uiModelList)
                binding.viewEmpty.root.visibility =
                    if (dataAnime.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvAnime) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = animeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}