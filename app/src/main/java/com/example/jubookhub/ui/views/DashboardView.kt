package com.example.jubookhub.ui.views

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.jubookhub.activities.UserProfileActivity // Import the UserProfileActivity class

@Composable
fun DashboardView(navController: NavController) {
    val context = LocalContext.current // Get the context for starting activities

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Dashboard", fontSize = 30.sp, modifier = Modifier.padding(bottom = 20.dp))

        // Button to show Academic Materials
        Button(
            onClick = { navController.navigate("academic_section") }, // Navigate to Academic Section
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("Academic Materials")
        }

        // Button to show Research Materials
        Button(
            onClick = { navController.navigate("research_section") }, // Navigate to Research Section
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("Research Materials")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Add a button to navigate to the Profile screen
        Button(onClick = {
            // Use the context to start the Profile Activity
            val intent = Intent(context, UserProfileActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Go to Profile")
        }
    }
}
