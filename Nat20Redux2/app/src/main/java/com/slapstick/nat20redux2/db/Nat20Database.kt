package com.slapstick.nat20redux2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.slapstick.nat20redux2.db.favorites.Favorite
import com.slapstick.nat20redux2.db.favorites.FavoritesDao
import com.slapstick.nat20redux2.db.journal.Journal
import com.slapstick.nat20redux2.db.journal.JournalDao

@Database(entities = [Favorite::class, Journal::class], version = 1, exportSchema = false)
abstract class Nat20Database: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
    abstract fun journalDao(): JournalDao

    companion object {
        @Volatile
        private var INSTANCE: Nat20Database? = null

        fun getDatabase(context: Context): Nat20Database {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Nat20Database::class.java,
                    "nat_20_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}