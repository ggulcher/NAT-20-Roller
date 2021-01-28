package com.slapstick.nat20redux2.db.journal

import androidx.lifecycle.LiveData

class JournalRepository(private val journalDao: JournalDao) {

    val readAllJournal: LiveData<List<Journal>> = journalDao.readAllJournal()

    // Journal Entries
    suspend fun addJournal(journal: Journal) { journalDao.addJournal(journal) }

    suspend fun updateJournal(journal: Journal) { journalDao.updateJournal(journal) }

    suspend fun deleteJournal(journal: Journal) { journalDao.deleteJournal(journal) }

    suspend fun deleteAllJournals() { journalDao.deleteAllJournals() }

}