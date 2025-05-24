package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ica.medminder.dal.entities.System

@Dao
interface SystemDao {
    @Query("SELECT * FROM system")
    suspend fun getAll(): List<System>
    @Insert
    suspend fun insert(vararg systems: System)
    @Update
    suspend fun update(vararg systems: System)
    @Query("DELETE FROM system")
    suspend fun delete()



}