package com.slapstick.nat20redux2.db.favorites

import androidx.lifecycle.LiveData

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    val readAllFavorites: LiveData<List<Favorite>> = favoritesDao.readAllFavorites()

    // Roll Favorites
    suspend fun addRoll(favorite: Favorite) { favoritesDao.addFavorite(favorite) }

    suspend fun updateRoll(favorite: Favorite) { favoritesDao.updateFavorite(favorite) }

    suspend fun deleteRoll(favorite: Favorite) { favoritesDao.deleteFavorite(favorite) }

    suspend fun deleteAllRolls() { favoritesDao.deleteAllFavorites() }

}