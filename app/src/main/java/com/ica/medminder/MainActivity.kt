package com.ica.medminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import com.ica.medminder.dal.AppDatabase
import com.ica.medminder.ui.theme.MedMinderTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isDataReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // Install the splash screen
        super.onCreate(savedInstanceState)

        // Keep the splash screen visible until data is ready
        splashScreen.setKeepOnScreenCondition { !isDataReady }

        lifecycleScope.launch {
            // Example: Your ViewModel might have a method that ensures DB is ready
            // mainViewModel.prepareInitialData() // This would internally access the DB
            // For this example, let's just simulate a delay for DB init
            try {
                // Accessing the database instance here (if not already done)
                // will trigger its creation by Hilt on a background thread.
                // The actual "work" is in AppDatabase.getInstance()
                AppDatabase.getInstance(applicationContext)

                isDataReady = true
            } catch (e: Exception) {
                // Handle DB initialization error
                isDataReady = true // Still allow app to proceed, maybe with error state
            }
        }
        enableEdgeToEdge()
        setContent {
            MedMinderTheme {
                if (isDataReady) {
                    App()
                } else {
                    // Optionally, show a more integrated loading UI within your Compose theme
                    // if the splash screen API's condition isn't enough or for older OS versions.
                    // However, setKeepOnScreenCondition is preferred.
                }
            }

        }
    }
}
