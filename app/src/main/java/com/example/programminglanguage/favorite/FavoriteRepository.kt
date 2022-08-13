package com.example.programminglanguage.Language

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.programminglanguage.favorite.FavoriteDao
import com.example.programminglanguage.favorite.FavoriteDatabase
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavDao = db.favoriteDao()
    }

    fun insert(Language: Language) {
        executorService.execute {
            mFavDao.insert(Language)
        }
    }

    fun getAll(): LiveData<List<Language>> = mFavDao.getAll()

    fun update(Language: Language) {
        executorService.execute {
            mFavDao.update(Language)
        }
    }

    fun delete(Language: Language) {
        executorService.execute {
            mFavDao.delete(Language)
        }
    }
}