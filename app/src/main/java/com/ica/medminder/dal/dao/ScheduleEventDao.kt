package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ica.medminder.dal.entities.ScheduleEvent

@Dao
interface ScheduleEventDao {
    @Query("SELECT * FROM scheduleEvent")
    suspend fun getAll(): List<ScheduleEvent>
    @Query("SELECT * FROM scheduleEvent WHERE id = :id")
    suspend fun getById(id: Int): ScheduleEvent
    @Query("SELECT * FROM scheduleEvent WHERE scheduleId = :scheduleId")
    suspend fun getByScheduleId(scheduleId: Int): List<ScheduleEvent>
    @Insert
    suspend fun insertAll(vararg scheduleEvents: ScheduleEvent)
    @Update
    suspend fun updateAll(vararg scheduleEvents: ScheduleEvent)
    @Query("DELETE FROM scheduleEvent")
    suspend fun deleteAll()
    @Query("DELETE FROM scheduleEvent WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Query("DELETE FROM scheduleEvent WHERE scheduleId = :scheduleId")
    suspend fun deleteByScheduleId(scheduleId: Int)
}