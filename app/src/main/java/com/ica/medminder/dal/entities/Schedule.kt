package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val patientId: Int,
    val medicationId: Int,
    val frequency: String,
    val dow: String,
    val morning: Boolean,
    val afternoon: Boolean,
    val evening: Boolean,
    val bedtime: Boolean,
    val notes: String,
    val status: String,
    val created: String,
    val modified: String
)
