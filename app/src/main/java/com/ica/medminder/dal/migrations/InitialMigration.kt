package com.ica.medminder.dal.migrations

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.ica.medminder.dal.AppDatabase
import com.ica.medminder.dal.dao.*
import com.ica.medminder.dal.entities.*

class InitialMigration(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val systemDao = AppDatabase.getInstance(applicationContext).systemDao()
            val systems = systemDao.getAll()
            if (systems.isEmpty()) {
                println("Inserting initial system: 1.0 - 2025-05-18")
                systemDao.insert(System(version = "1.0", releaseDate = "2025-05-18"))
            }

            val userPreferencesDao = AppDatabase.getInstance(applicationContext).userPreferencesDao()
            val userPreferences = userPreferencesDao.getAllStateless()
            if (userPreferences.isEmpty()) {
                println("Inserting initial user preference: startup = Now")
                userPreferencesDao.insert(UserPreferences(startup = "Now"))
            }

            Result.success()
        }
        catch (e: Exception) {
            Result.failure()
        }
    }
}