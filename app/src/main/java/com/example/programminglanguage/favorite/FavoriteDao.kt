package com.example.programminglanguage.favorite

import androidx.lifecycle.LiveData
import androidx.room.*
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Language)

    @Query("SELECT * FROM language")
    fun getAll(): List<Language>

    @Update
    fun update(favorite: Language)

    @Delete
    fun delete(favorite: Language)
}