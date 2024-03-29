package com.example.programminglanguage.favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.example.programminglanguage.model.Favorite
import com.example.programminglanguage.model.Language

@Database(
    entities = [
        Language::class
    ],
    exportSchema = false,
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_db"
                    ).build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}