package com.slapstick.nat20redux2.db.journal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "journal_table")
class Journal(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val title: String,
        val content: String
): Parcelable