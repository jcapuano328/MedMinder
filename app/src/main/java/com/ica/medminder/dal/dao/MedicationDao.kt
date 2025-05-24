package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ica.medminder.dal.entities.Medication

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication")
    suspend fun getAll(): List<Medication>
    @Query("SELECT * FROM medication WHERE id = :id")
    suspend fun getById(id: Int): Medication
    @Insert
    suspend fun insertAll(vararg medications: Medication)
    @Update
    suspend fun updateAll(vararg medications: Medication)
    @Query("DELETE FROM medication")
    suspend fun deleteAll()
    @Query("DELETE FROM medication WHERE id = :id")
    suspend fun deleteById(id: Int)
}