package com.example.jubookhub.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jubookhub.ui.components.AcademicSection
import com.example.jubookhub.ui.components.ResearchSection
import com.example.jubookhub.ui.components.YearMaterialScreen
import com.example.jubookhub.ui.views.DashboardView

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                // Set up NavController for Compose navigation
                val navController = rememberNavController()

                // Define Navigation for Compose-based UI
                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        DashboardView(navController) // Pass the NavController here
                    }
                    composable("academic_section") {
                        // AcademicSection now requires 'navController'
                        AcademicSection(navController = navController)
                    }
                    composable("research_section") {
                        ResearchSection()
                    }
                    
                    // New route for the year-wise material screen
                    // It accepts a 'year' argument
                    composable(
                        route = "year_material_screen/{year}",
                        arguments = listOf(navArgument("year") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val year = backStackEntry.arguments?.getInt("year") ?: 1
                        YearMaterialScreen(navController, year)
                    }
                }
            }
        }
    }
}
