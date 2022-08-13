package com.example.programminglanguage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Favorite::class
    ],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_db"
                ).build().also {
                    INSTANCE = it
                }

            }
        }
    }
}