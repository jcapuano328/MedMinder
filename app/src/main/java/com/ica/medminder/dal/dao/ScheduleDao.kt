package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ica.medminder.dal.entities.Schedule

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    suspend fun getAll(): List<Schedule>
    @Query("SELECT * FROM schedule WHERE id = :id")
    suspend fun getById(id: Int): Schedule
    @Query("SELECT * FROM schedule WHERE medicationId = :medicationId")
    suspend fun getByMedicationId(medicationId: Int): List<Schedule>
    @Insert
    suspend fun insertAll(vararg schedules: Schedule)
    @Update
    suspend fun updateAll(vararg schedules: Schedule)
    @Query("DELETE FROM schedule")
    suspend fun deleteAll()
    @Query("DELETE FROM schedule WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Query("DELETE FROM schedule WHERE medicationId = :medicationId")
    suspend fun deleteByMedicationId(medicationId: Int)
}