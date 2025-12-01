package com.example.jubookhub.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jubookhub.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setContent {
            RegistrationScreen()
        }
    }

    @Composable
    fun RegistrationScreen() {

        var fullName by remember { mutableStateOf("") }
        var studentId by remember { mutableStateOf("") }
        var eduEmail by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var isLoading by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }

        // Background Gradient (MATCHING LOGIN)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF4A148C),
                            Color(0xFF7B1FA2),
                            Color(0xFFE1BEE7)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                // 🔥 Logo
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    modifier = Modifier.size(110.dp).alpha(0.9f)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Join JU Book Hub",
                    fontSize = 28.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(25.dp))

                // GLASS CARD CONTAINER
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White.copy(alpha = 0.15f))
                        .padding(24.dp)
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Full Name
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = outlinedFieldColors()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Student ID
                    OutlinedTextField(
                        value = studentId,
                        onValueChange = { studentId = it },
                        label = { Text("Student ID (9 digits)") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = outlinedFieldColors()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // EDU Email
                    OutlinedTextField(
                        value = eduEmail,
                        onValueChange = { eduEmail = it },
                        label = { Text("University EDU Email") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = Color.White)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = outlinedFieldColors()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = outlinedFieldColors()
                    )

                    if (errorMessage.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = errorMessage, color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // REGISTER BUTTON
                    Button(
                        onClick = {
                            if (fullName.isBlank() || studentId.isBlank() ||
                                eduEmail.isBlank() || password.isBlank()
                            ) {
                                errorMessage = "All fields are required!"
                            } else {
                                errorMessage = ""
                                performRegistration(
                                    fullName,
                                    studentId,
                                    eduEmail,
                                    password
                                ) { isLoading = it }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                strokeWidth = 3.dp,
                                color = Color(0xFF6A1B9A)
                            )
                        } else {
                            Text(
                                "Register",
                                fontSize = 18.sp,
                                color = Color(0xFF6A1B9A),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Already have account?
                    Row(
                        modifier = Modifier.clickable {
                            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                        }
                    ) {
                        Text("Already have an account?", color = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Login", color = Color.Yellow, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                // Bottom Back Circle Button
                IconButton(
                    onClick = { finish() },
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White.copy(alpha = 0.3f), shape = CircleShape)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }
        }
    }

    @Composable
    private fun outlinedFieldColors() = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )

    private fun performRegistration(
        fullName: String,
        studentId: String,
        eduEmail: String,
        password: String,
        setLoading: (Boolean) -> Unit
    ) {
        setLoading(true)

        if (studentId.length != 9) {
            showToast("Student ID must be 9 digits")
            setLoading(false)
            return
        }
        if (!eduEmail.contains("@")) {
            showToast("Invalid email")
            setLoading(false)
            return
        }
        if (password.length < 6) {
            showToast("Password must be 6+ characters")
            setLoading(false)
            return
        }

        auth.createUserWithEmailAndPassword(eduEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    val userMap = hashMapOf(
                        "name" to fullName,
                        "student_id" to studentId,
                        "email" to eduEmail,
                        "role" to "student"
                    )

                    db.collection("users").document(userId)
                        .set(userMap)
                        .addOnSuccessListener {
                            setLoading(false)
                            showToast("Registration Successful! Verify your email.")
                            auth.currentUser?.sendEmailVerification()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            setLoading(false)
                            showToast("Database error!")
                        }
                } else {
                    setLoading(false)
                    showToast("Failed: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
