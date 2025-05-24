package com.ica.medminder.navigation

sealed class NavigationDestinations(val route: String, val icon: Int) {
    object Schedule : NavigationDestinations("Schedule", com.ica.medminder.R.drawable.schedule)
    object Patients : NavigationDestinations("Patients", com.ica.medminder.R.drawable.patients)
    object Reminders : NavigationDestinations("Reminders", com.ica.medminder.R.drawable.reminders)
    object Settings : NavigationDestinations("Settings", com.ica.medminder.R.drawable.settings)
    object About : NavigationDestinations("About", com.ica.medminder.R.drawable.info)
}