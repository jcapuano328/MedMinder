package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class System(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val version: String,
    val releaseDate: String
)