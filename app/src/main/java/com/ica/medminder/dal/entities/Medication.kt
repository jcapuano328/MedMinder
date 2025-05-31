package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val patientId: Int,
    val name: String,
    val dosage: String,
    val instructions: String,
    val status: String,
    val created: String,
    val modified: String
)
