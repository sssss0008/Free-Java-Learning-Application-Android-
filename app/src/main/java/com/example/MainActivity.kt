package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.data.UserPreferencesRepository
import com.example.ui.MainAppScreen
import com.example.ui.theme.LearnJavaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = UserPreferencesRepository(applicationContext)

        setContent {
            val userProfile by repository.userProfile.collectAsState()
            LearnJavaTheme(themePreference = userProfile.themeMode) {
                MainAppScreen(repository = repository)
            }
        }
    }
}
