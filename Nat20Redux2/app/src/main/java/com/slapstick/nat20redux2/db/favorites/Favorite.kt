package com.slapstick.nat20redux2.db.favorites

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "favorites_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val titleFave: String,
    val rollFave: String,
): Parcelable