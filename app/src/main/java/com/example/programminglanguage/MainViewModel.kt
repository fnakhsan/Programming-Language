package com.example.programminglanguage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.programminglanguage.favorite.FavoriteRepository
import com.example.programminglanguage.model.Language

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: FavoriteRepository = FavoriteRepository(application)

    fun getAll(): LiveData<List<Language>> = repository.getAll()
}