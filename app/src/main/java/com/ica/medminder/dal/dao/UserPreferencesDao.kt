package com.ica.medminder.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.ica.medminder.dal.entities.UserPreferences

@Dao
interface UserPreferencesDao {
    @Query("SELECT * FROM userpreferences")
    fun getAll(): Flow<List<UserPreferences>>
    @Query("SELECT * FROM userpreferences")
    fun getAllStateless(): List<UserPreferences>
    @Insert
    suspend fun insert(vararg userPreferences: UserPreferences)
    @Update
    suspend fun update(vararg userPreferences: UserPreferences)
    @Query("DELETE FROM userpreferences")
    suspend fun delete()



}