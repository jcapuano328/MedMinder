package com.ica.medminder.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScheduleEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val scheduleId: Int,
    val notes: String,
    val status: String,
    val created: String,
    val modified: String
)
