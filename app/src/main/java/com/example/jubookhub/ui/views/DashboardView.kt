package com.example.jubookhub.ui.views

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jubookhub.activities.UserProfileActivity

@Composable
fun DashboardView(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to Dashboard",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1E3A8A),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        GradientButton(
            text = "Academic Materials",
            gradient = Brush.horizontalGradient(listOf(Color(0xFF3B82F6), Color(0xFF60A5FA)))
        ) {
            navController.navigate("academic_section")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GradientButton(
            text = "Research Materials",
            gradient = Brush.horizontalGradient(listOf(Color(0xFF8B5CF6), Color(0xFFC084FC)))
        ) {
            navController.navigate("research_section")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GradientButton(
            text = "Go to Profile",
            gradient = Brush.horizontalGradient(listOf(Color(0xFF10B981), Color(0xFF6EE7B7)))
        ) {
            val intent = Intent(context, UserProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}

@Composable
fun GradientButton(text: String, gradient: Brush, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
