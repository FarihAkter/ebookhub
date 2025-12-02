package com.example.jubookhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AcademicSection(navController: NavController) {

    val gradientHeader = Brush.horizontalGradient(
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

        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, RoundedCornerShape(18.dp))
                .background(gradientHeader, RoundedCornerShape(18.dp))
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Academic Materials",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(25.dp))


        // YEAR CARDS
        AcademicYearCard("1st Year") {
            navController.navigate("year_material_screen/1")
        }
        AcademicYearCard("2nd Year") {
            navController.navigate("year_material_screen/2")
        }
        AcademicYearCard("3rd Year") {
            navController.navigate("year_material_screen/3")
        }
        AcademicYearCard("4th Year") {
            navController.navigate("year_material_screen/4")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // OFFLINE SECTION
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(55.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0EA5E9)
            )
        ) {
            Text(
                text = "Offline Availability",
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}


// 🔥 PROFESSIONAL CARD DESIGN FOR YEAR BUTTONS
@Composable
fun AcademicYearCard(title: String, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
        }
    }
}
