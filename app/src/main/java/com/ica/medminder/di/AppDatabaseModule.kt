package com.ica.medminder.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.ica.medminder.dal.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Provides
    fun providePatientDao(appDatabase: AppDatabase) = appDatabase.patientDao()
    @Provides
    fun provideMedicationDao(appDatabase: AppDatabase) = appDatabase.medicationDao()
    @Provides
    fun provideScheduleDao(appDatabase: AppDatabase) = appDatabase.scheduleDao()
    @Provides
    fun provideScheduleEventDao(appDatabase: AppDatabase) = appDatabase.scheduleEventDao()
    @Provides
    fun provideSystemDao(appDatabase: AppDatabase) = appDatabase.systemDao()
    @Provides
    fun provideUserPreferencesDao(appDatabase: AppDatabase) = appDatabase.userPreferencesDao()

    // @Provides // Provide your DAOs here as well
    // fun provideUserDao(appDatabase: AppDatabase): UserDao {
    //     return appDatabase.userDao()
    // }
}