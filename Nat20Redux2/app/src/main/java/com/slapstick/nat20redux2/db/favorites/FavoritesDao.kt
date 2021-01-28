package com.slapstick.nat20redux2.db.favorites

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: Favorite)

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAllFavorites()

    @Query("SELECT * FROM favorites_table ORDER BY id ASC")
    fun readAllFavorites(): LiveData<List<Favorite>>

}