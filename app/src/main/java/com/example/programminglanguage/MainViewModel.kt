package com.example.programminglanguage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.programminglanguage.Language.FavoriteRepository
import com.example.programminglanguage.model.Language

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: FavoriteRepository = FavoriteRepository(application)

    fun getAll(): List<Language> = repository.getAll()
}