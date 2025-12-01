package com.example.jubookhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookItem(bookTitle: String, author: String) {
    // Card container to display book information with elevation and rounded corners
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Padding between items
        shape = RoundedCornerShape(12.dp), // Rounded corners for the Card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // CORRECTED: cardElevation instead of elevation
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface) // Card background color
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding inside the Card
                .fillMaxWidth(), // Ensures content takes full width
            verticalArrangement = Arrangement.Center // Vertically center the content
        ) {
            // Title: Make the title bold and larger font size
            Text(
                text = bookTitle,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp) // Padding for title
            )

            // Author name: Gray color and smaller font size
            Text(
                text = "by $author",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 12.dp) // Padding for author
            )
        }
    }
}
