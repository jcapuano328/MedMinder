package com.ica.medminder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.RowScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

import com.ica.medminder.screens.ScheduleScreen
import com.ica.medminder.screens.PatientsScreen
import com.ica.medminder.screens.RemindersScreen
import com.ica.medminder.screens.SettingsScreen
import com.ica.medminder.ui.PngIcon

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        MainNavigationContent(navController, innerPadding)
    }
}

@Composable
fun MainNavigationContent(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.Schedule.route,
        modifier = Modifier.padding(innerPadding) // Apply padding here
    ) {
        composable(NavigationDestinations.Schedule.route) { ScheduleScreen(navController) }
        composable(NavigationDestinations.Patients.route) { PatientsScreen(navController) }
        composable(NavigationDestinations.Reminders.route) { RemindersScreen(navController) }
        composable(NavigationDestinations.Settings.route) { SettingsScreen(navController) }
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        actions = {
            MyNavigationItems(navController, currentDestination)
        },
        modifier = Modifier,
        tonalElevation = 10.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
fun RowScope.MyNavigationItems(navController: NavHostController, currentDestination: NavDestination?) {
    listOf(
        NavigationDestinations.Schedule,
        NavigationDestinations.Patients,
        NavigationDestinations.Reminders,
        NavigationDestinations.Settings,
    ).forEach { destination ->
        NavigationBarItem(
            //icon = { FontAwesomeIcon(unicode = destination.icon, description = destination.route) },
            icon = { PngIcon(resId = destination.icon, desc = destination.route) },
            label = { Text(text = destination.route) },
            selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
            onClick = {
                navController.navigate(destination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = Color.White,
                selectedTextColor = Color.Red,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}
