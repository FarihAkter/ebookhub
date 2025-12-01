package com.example.jubookhub.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jubookhub.R
import com.example.jubookhub.ui.components.AcademicSection
import com.example.jubookhub.ui.components.ResearchSection

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "dashboard") {
                composable("dashboard") { NeonDashboard(navController) }
                composable("academic_section") { AcademicSection(navController) }
                composable("research_section") { ResearchSection() }
            }
        }
    }
}

@Composable
fun NeonDashboard(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF4A148C), Color(0xFF6A1B9A), Color(0xFFE1BEE7))
                )
            )
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        ) {

            // Floating JU Logo
            val infiniteTransition = rememberInfiniteTransition()
            val logoOffset by infiniteTransition.animateFloat(
                initialValue = -12f,
                targetValue = 12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ju_logo),
                contentDescription = "JU Logo",
                modifier = Modifier
                    .size(120.dp)
                    .offset(y = logoOffset.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Jahangirnagar University\nBook Hub",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = 34.sp,
                modifier = Modifier.padding(horizontal = 12.dp),
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Neon Buttons
            NeonButton("Academic Section", Color(0xFF7B1FA2)) {
                navController.navigate("academic_section")
            }

            Spacer(modifier = Modifier.height(30.dp))

            NeonButton("Research Section", Color(0xFF6A1B9A)) {
                navController.navigate("research_section")
            }
        }
    }
}

@Composable
fun NeonButton(title: String, baseColor: Color, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .scale(scale)
            .clickable { onClick() }
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(25.dp),
                ambientColor = baseColor.copy(alpha = glowAlpha),
                spotColor = baseColor.copy(alpha = glowAlpha)
            )
            .graphicsLayer {
                alpha = 0.98f
            },
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = baseColor)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
