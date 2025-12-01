package com.example.jubookhub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AcademicSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Academic Materials", fontSize = 30.sp, modifier = Modifier.padding(bottom = 20.dp))

        // Year-wise buttons to navigate to the YearMaterialScreen
        Button(
            onClick = { navController.navigate("year_material_screen/1") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("1st Year")
        }

        Button(
            onClick = { navController.navigate("year_material_screen/2") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("2nd Year")
        }

        Button(
            onClick = { navController.navigate("year_material_screen/3") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("3rd Year")
        }

        Button(
            onClick = { navController.navigate("year_material_screen/4") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text("4th Year")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Non-functional Offline Availability Button
        Button(onClick = { /* Add functionality later */ }, modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Text("Offline Availability")
        }
    }
}
