package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dob: String,
    val status: String,
    val created: String,
    val modified: String
)
