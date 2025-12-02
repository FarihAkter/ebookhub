package com.example.jubookhub.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResearchSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Research Materials",
            fontSize = 30.sp,
            color = Color(0xFF1E293B),
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Explore modern research papers, books, and study materials.",
            fontSize = 16.sp,
            color = Color(0xFF475569),
            modifier = Modifier.padding(bottom = 25.dp)
        )

        // ---- MACHINE LEARNING ---- //
        CategoryTitle("Machine Learning & AI")
        ResearchItem("Hands-on Machine Learning", "Aurélien Géron",
            "A practical guide to ML using Scikit-Learn, Keras & TensorFlow.")
        ResearchItem("Deep Learning (MIT Press)", "Ian Goodfellow",
            "The most authoritative book on deep learning.")
        ResearchItem("AI in Healthcare", "Jane Smith",
            "AI applications and prediction models in medical sciences.")

        // ---- DATA SCIENCE ---- //
        CategoryTitle("Data Science & Big Data")
        ResearchItem("Data Science from Scratch", "Joel Grus",
            "Fundamentals of data science and statistical modeling.")
        ResearchItem("Big Data Analytics", "Tom White",
            "A complete guide to Hadoop, MapReduce, and Big Data pipelines.")

        // ---- QUANTUM COMPUTING ---- //
        CategoryTitle("Quantum Computing")
        ResearchItem("Quantum Computing 101", "Albert Einstein",
            "Foundations of quantum information processing.")
        ResearchItem("Qubits & Quantum Algorithms", "David Deutsch",
            "Understanding qubits, entanglement, and quantum logic.")

        // ---- CYBERSECURITY ---- //
        CategoryTitle("Cyber Security & Networking")
        ResearchItem("Network Security Essentials", "William Stallings",
            "Basics of cryptography, cybersecurity and secure protocols.")
        ResearchItem("Cyber Threat Intelligence", "Bruce Schneier",
            "Real-world cyber threat modeling & defense.")

        // ---- NLP ---- //
        CategoryTitle("Natural Language Processing")
        ResearchItem("Speech & Language Processing", "Jurafsky & Martin",
            "Bible of NLP covering classical + deep models.")
        ResearchItem("Transformers & LLMs", "OpenAI Research",
            "Understanding GPT models, attention, tokenization & inference.")
    }
}


// ------------------ COMPONENTS ---------------------------- //

@Composable
fun CategoryTitle(text: String) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0F172A),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}


@Composable
fun ResearchItem(title: String, author: String, description: String) {
    val ctx = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3B82F6).copy(alpha = 0.08f),
                            Color.White
                        )
                    )
                )
                .padding(20.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "By: $author",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF475569),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFF334155),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    Toast.makeText(ctx, "Opening $title", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("View Details", color = Color.White)
            }
        }
    }
}
