package com.example.programminglanguage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.programminglanguage.Language.FavoriteRepository
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Language) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favorite)
    }

    fun delete(favorite: Language) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favorite)
    }
}