//package com.example.ibarsanimelist.core.ui
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.ibarsanimelist.core.di.Injection
//import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase
//import com.example.ibarsanimelist.ui.detail.DetailAnimeViewModel
//import com.example.ibarsanimelist.ui.home.HomeViewModel
//
//class ViewModelFactory(
//    private val animeUseCase: AnimeUseCase
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
////            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
////                MainViewModel(authUseCase) as T
////            }
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
//                HomeViewModel(animeUseCase) as T
//            }
//            modelClass.isAssignableFrom(DetailAnimeViewModel::class.java) -> {
//                DetailAnimeViewModel(animeUseCase) as T
//            }
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//        @JvmStatic
//        fun getInstance(context: Context): ViewModelFactory {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: ViewModelFactory(
//                    Injection.provideAnimeUseCase(context)
//                ).also { INSTANCE = it }
//            }
//        }
//    }
//}