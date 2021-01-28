package com.slapstick.nat20redux2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.slapstick.nat20redux2.db.Nat20Database
import com.slapstick.nat20redux2.db.favorites.Favorite
import com.slapstick.nat20redux2.db.favorites.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): AndroidViewModel(application) {

    val readAllFavorites: LiveData<List<Favorite>>
    private val repository: FavoritesRepository

    init {
        val favoritesDao = Nat20Database.getDatabase(application).favoritesDao()
        repository = FavoritesRepository(favoritesDao)
        readAllFavorites = repository.readAllFavorites
    }

    fun addRoll(favorite: Favorite) { viewModelScope.launch(Dispatchers.IO) { repository.addRoll(favorite) } }

    fun updateRoll(favorite: Favorite) { viewModelScope.launch(Dispatchers.IO) { repository.updateRoll(favorite) } }

    fun deleteRoll(favorite: Favorite) { viewModelScope.launch(Dispatchers.IO) { repository.deleteRoll(favorite) } }

    fun deleteAllRolls() { viewModelScope.launch(Dispatchers.IO) { repository.deleteAllRolls() } }

}