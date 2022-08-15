package com.example.programminglanguage.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.programminglanguage.favorite.FavoriteRepository
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Language) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favorite)
    }

    fun isFavorite(name: String): Boolean = repository.countFav(name) >= 1

    fun delete(favorite: Language) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favorite)
    }
}