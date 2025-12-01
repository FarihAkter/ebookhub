package com.example.jubookhub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun YearMaterialScreen(navController: NavController, year: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$year Year Materials",
            fontSize = 30.sp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Display materials for the selected year
        // This now uses the shared BookItem from BookItem.kt
        BookItem(bookTitle = "Book Title for Year $year", author = "Author $year")
        BookItem(bookTitle = "Handouts for Year $year", author = "Author $year")
        BookItem(bookTitle = "Previous Year Questions for Year $year", author = "Author $year")
        BookItem(bookTitle = "Slides for Year $year", author = "Author $year")

        Spacer(modifier = Modifier.height(20.dp))

        // Add a button to navigate back to the AcademicSection
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Academic Section")
        }
    }
}
