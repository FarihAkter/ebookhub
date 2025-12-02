package com.example.jubookhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun YearMaterialScreen(navController: NavController, year: Int) {

    // Gradient Top Bar
    val headerGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF3B82F6),
            Color(0xFF06B6D4)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, RoundedCornerShape(18.dp))
                .background(headerGradient, RoundedCornerShape(18.dp))
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$Yearᵗʰ Year Materials",
                fontSize = 28.sp,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // MATERIAL SECTIONS (using your upgraded BookItem UI)
        MaterialSectionCard(
            title = "Books",
            subtitle = "Recommended & Course Books",
        )
        MaterialSectionCard(
            title = "Handouts",
            subtitle = "Teacher Provided PDFs & Notes",
        )
        MaterialSectionCard(
            title = "Question Bank",
            subtitle = "Previous Year & Model Questions",
        )
        MaterialSectionCard(
            title = "Lecture Slides",
            subtitle = "Teacher Uploaded PPT & PDFs",
        )

        Spacer(modifier = Modifier.height(40.dp))

        // BACK BUTTON
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3B82F6)
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(55.dp)
        ) {
            Text(
                text = "Back to Academic Section",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}


/// 🔥 PROFESSIONAL MATERIAL CARD
@Composable
fun MaterialSectionCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                color = Color(0xFF1E293B),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                fontSize = 15.sp,
                color = Color(0xFF64748B)
            )
        }
    }
}
