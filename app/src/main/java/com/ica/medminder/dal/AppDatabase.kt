package com.ica.medminder.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ica.medminder.dal.dao.*
import com.ica.medminder.dal.entities.*

@Database(entities = [Patient::class, Medication::class, Schedule::class, ScheduleEvent::class, System::class, UserPreferences::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun medicationDao(): MedicationDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun scheduleEventDao(): ScheduleEventDao
    abstract fun systemDao(): SystemDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        // Volatile annotation ensures that writes to this field are immediately
        // visible to other threads.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a time.
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "medminder_database" // Choose a suitable name for your database file
                    )
                        // Add database migrations if necessary
                        // .addMigrations(MIGRATION_1_2)
                        // You can also configure other options like pre-populating the database
                        // .createFromAsset("database/myapp.db")
                        .build()
                    INSTANCE = instance

                    initialize(context)
                }
                return instance
            }
        }
        private fun initialize(context: Context) {
            val request = OneTimeWorkRequestBuilder<com.ica.medminder.dal.migrations.InitialMigration>()
                .build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}