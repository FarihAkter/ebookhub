package com.example.jubookhub.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registration Form", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty() && fullName.isEmpty()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text("Student ID") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty() && studentId.isEmpty()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = eduEmail,
                onValueChange = { eduEmail = it },
                label = { Text("EDU Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty() && eduEmail.isEmpty()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty() && password.length < 6
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        // Input validation
                        if (fullName.isBlank() || studentId.isBlank() || eduEmail.isBlank() || password.isBlank()) {
                            errorMessage = "All fields are required"
                            return@Button
                        }
                        registerUser(
                            fullName.trim(),
                            studentId.trim(),
                            eduEmail.trim(),
                            password.trim()
                        ) { isLoading = it }
                    }
                ) {
                    Text("Register")
                }
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }

    private fun registerUser(
        fullName: String,
        studentId: String,
        eduEmail: String,
        password: String,
        setLoading: (Boolean) -> Unit
    ) {
        // Input Validation
        if (fullName.isEmpty()) return showToast("Full name is required")
        if (studentId.isEmpty()) return showToast("Student ID is required")
        if (studentId.length != 9) return showToast("Invalid student ID (must be 9 digits)")
        if (eduEmail.isEmpty()) return showToast("EDU email is required")

        if (!eduEmail.endsWith("@juniv.edu") &&
            !eduEmail.endsWith("@student.juniv.edu") &&
            !eduEmail.endsWith("@ju.edu.bd")) {
            return showToast("Use your official EDU email")
        }

        if (password.length < 6) return showToast("Password must be at least 6 characters")

        setLoading(true)
        Log.d("Registration", "Starting registration for $eduEmail")

        // Firebase Register
        auth.createUserWithEmailAndPassword(eduEmail, password)
            .addOnCompleteListener { task ->
                setLoading(false) // Stop loading once the task finishes

                if (!task.isSuccessful) {
                    Log.e("Registration", "Auth failed", task.exception)
                    return@addOnCompleteListener showToast(
                        "Registration failed: ${task.exception?.message}"
                    )
                }

                Log.d("Registration", "Auth successful, user: ${auth.currentUser?.uid}")

                // SAFELY get user
                val userId = auth.currentUser?.uid
                if (userId == null) {
                    return@addOnCompleteListener showToast("Unexpected Error! Try again.")
                }

                val userData = hashMapOf(
                    "name" to fullName,
                    "student_id" to studentId,
                    "email" to eduEmail,
                    "role" to "student"
                )

                Log.d("Registration", "Saving to Firestore...")

                // Save Firestore Data
                db.collection("users").document(userId)
                    .set(userData)
                    .addOnSuccessListener {
                        Log.d("Registration", "Firestore save successful")

                        // Send email verification
                        auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { emailTask ->

                                if (emailTask.isSuccessful) {
                                    showToast("Registration Successful! Verify your email.")

                                    // SAFELY navigate
                                    try {
                                        startActivity(Intent(this, LoginActivity::class.java))
                                        finish() // Close the RegistrationActivity
                                    } catch (e: Exception) {
                                        Log.e("Registration", "Navigation failed", e)
                                    }

                                } else {
                                    Log.e("Registration", "Email sending failed", emailTask.exception)
                                    showToast("Error sending verification email.")
                                }
                            }
                    }
                    .addOnFailureListener {
                        Log.e("Registration", "Firestore save failed", it)
                        showToast("Error saving user data: ${it.message}")
                    }
            }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
