package com.example.jubookhub.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResearchSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Research Materials",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Browse through the latest research materials for academic growth.",
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {

            ResearchItem(
                title = "Research Book 1: Advanced Machine Learning",
                author = "John Doe",
                description = "An in-depth study of modern machine learning techniques."
            )

            ResearchItem(
                title = "Research Paper: AI in Healthcare",
                author = "Jane Smith",
                description = "Exploring the role of artificial intelligence in the healthcare sector."
            )

            ResearchItem(
                title = "Research Paper: Quantum Computing",
                author = "Albert Einstein",
                description = "Understanding the fundamentals of quantum computing."
            )
        }
    }
}

@Composable
fun ResearchItem(
    title: String,
    author: String,
    description: String
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "By: $author",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Button(
                onClick = {
                    Toast.makeText(context, "Opening $title", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("View Details")
            }
        }
    }
}
