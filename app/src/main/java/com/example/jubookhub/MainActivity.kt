package com.example.jubookhub

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jubookhub.activities.LoginActivity
import com.example.jubookhub.activities.RegistrationActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FancyWelcomeScreen() }
    }
}

@Composable
fun FancyWelcomeScreen() {
    val context = LocalContext.current

    // Gradient background
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF3B82F6), Color(0xFF06B6D4))
    )

    Box(modifier = Modifier.fillMaxSize().background(gradientBrush)) {

        // Floating background shapes
        FloatingShapes()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Glassmorphic-like header
            Box(
                modifier = Modifier
                    .shadow(10.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "📖 JU EBOOK HUB",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your gateway to academic excellence",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFE0F2FE)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Login Button
            FancyButton(
                text = "Login",
                gradient = Brush.horizontalGradient(listOf(Color(0xFF3B82F6), Color(0xFF60A5FA)))
            ) {
                context.startActivity(Intent(context, LoginActivity::class.java))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Register Button
            FancyButton(
                text = "Register",
                gradient = Brush.horizontalGradient(listOf(Color(0xFF10B981), Color(0xFF6EE7B7)))
            ) {
                context.startActivity(Intent(context, RegistrationActivity::class.java))
            }
        }
    }
}

@Composable
fun FancyButton(text: String, gradient: Brush, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .shadow(if (pressed) 4.dp else 10.dp, RoundedCornerShape(16.dp))
            .background(gradient, RoundedCornerShape(16.dp))
            .clickable {
                pressed = true
                onClick()
            }
            .animateContentSize(animationSpec = spring())
            .padding(vertical = 4.dp),
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

@Composable
fun FloatingShapes() {
    val shapeCount = 10
    val shapes = remember {
        List(shapeCount) {
            ShapeData(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 80 + 40,
                color = Color.White.copy(alpha = Random.nextFloat() * 0.15f + 0.05f)
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val offsets = shapes.map { shape ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = Random.nextInt(4000, 8000)),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        shapes.forEachIndexed { index, shape ->
            val offsetY = shape.y * size.height + offsets[index].value * 50
            val offsetX = shape.x * size.width
            drawCircle(
                color = shape.color,
                radius = shape.size / 2,
                center = Offset(offsetX, offsetY)
            )
        }
    }
}

data class ShapeData(
    val x: Float,
    val y: Float,
    val size: Float,
    val color: Color
)
