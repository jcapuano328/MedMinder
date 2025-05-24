package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ica.medminder.dal.entities.Patient

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    suspend fun getAll(): List<Patient>
    @Query("SELECT * FROM patient WHERE id = :id")
    suspend fun getById(id: Int): Patient
    @Insert
    suspend fun insertAll(vararg patients: Patient)
    @Update
    suspend fun updateAll(vararg patients: Patient)
    @Query("DELETE FROM patient")
    suspend fun deleteAll()
    @Query("DELETE FROM patient WHERE id = :id")
    suspend fun deleteById(id: Int)
}