package com.example.jubookhub.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    ResearchSection() // If ResearchSection requires navController, pass it as well
                }

                // New route for the year-wise material screen
                // It accepts a 'year' argument
                composable(
                    route = "year_material_screen/{year}",
                    arguments = listOf(navArgument("year") { type = NavType.IntType })
                ) { backStackEntry ->
                    // Extract 'year' from the arguments, with a fallback to 1 if no value is passed
                    val year = backStackEntry.arguments?.getInt("year") ?: 1
                    // Pass the navController and extracted year to the screen
                    YearMaterialScreen(navController, year)
                }
            }
        }
    }
}
