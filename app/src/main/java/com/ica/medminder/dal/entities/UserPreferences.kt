package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPreferences(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startup: String
)