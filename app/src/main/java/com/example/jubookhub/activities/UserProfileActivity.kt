package com.example.jubookhub.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudentProfileUI() }
    }
}

@Composable
fun StudentProfileUI() {
    val context = LocalContext.current
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    var name by remember { mutableStateOf("Loading…") }
    var email by remember { mutableStateOf("Loading…") }
    var studentId by remember { mutableStateOf("Loading…") }
    var department by remember { mutableStateOf("Loading…") }
    var batch by remember { mutableStateOf("Loading…") }
    var imageUrl by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        userId?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    name = doc.getString("name") ?: "Unknown"
                    email = doc.getString("email") ?: "Unknown"
                    studentId = doc.getString("student_id") ?: "Unknown"
                    department = doc.getString("department") ?: "CSE"
                    batch = doc.getString("batch") ?: "2020-24"
                    imageUrl = doc.getString("profileImageUrl") ?: ""
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    val backgroundGradient = Brush.verticalGradient(
        listOf(Color(0xFF2563EB), Color(0xFF0EA5E9))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            // Title badge
            Box(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(
                    "🎓 Student Profile",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Profile Image
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(700))
            ) {
                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .shadow(8.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Person),
                        contentDescription = "Profile Placeholder",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Info Card
            InfoCard(title = "Name", value = name)
            InfoCard(title = "Email", value = email)
            InfoCard(title = "Student ID", value = studentId)
            InfoCard(title = "Department", value = department)
            InfoCard(title = "Batch", value = batch)

            Spacer(modifier = Modifier.height(30.dp))

            LogoutButton {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(title, color = Color.White.copy(0.7f), fontSize = 14.sp)
            Text(
                value,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit) {
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
            Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFEF4444), Color(0xFFF87171))
                    ), RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Logout", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
