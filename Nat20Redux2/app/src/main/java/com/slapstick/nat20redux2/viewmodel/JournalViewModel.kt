package com.slapstick.nat20redux2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.slapstick.nat20redux2.db.Nat20Database
import com.slapstick.nat20redux2.db.journal.Journal
import com.slapstick.nat20redux2.db.journal.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JournalViewModel(application: Application): AndroidViewModel(application) {

    val readAllJournal: LiveData<List<Journal>>
    private val repository: JournalRepository

    init {
        val journalDao = Nat20Database.getDatabase(application).journalDao()
        repository = JournalRepository(journalDao)
        readAllJournal = repository.readAllJournal
    }

    fun addJournal(journal: Journal) { viewModelScope.launch(Dispatchers.IO) { repository.addJournal(journal) } }

    fun updateJournal(journal: Journal) { viewModelScope.launch(Dispatchers.IO) { repository.updateJournal(journal) } }

    fun deleteJournal(journal: Journal) { viewModelScope.launch(Dispatchers.IO) { repository.deleteJournal(journal) } }

    fun deleteAllJournals() { viewModelScope.launch(Dispatchers.IO) { repository.deleteAllJournals() } }

}