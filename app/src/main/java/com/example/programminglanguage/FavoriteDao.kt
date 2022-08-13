package com.example.programminglanguage

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favorite: Favorite)

    @Query("DELETE FROM Favorite WHERE Favorite.name = :name")
    suspend fun delete(name: String)
}