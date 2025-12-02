package com.example.jubookhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jubookhub.models.Book

@Composable
fun BookItemPremium(book: Book) {

    var bookmarked by remember { mutableStateOf(book.isBookmarked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .shadow(12.dp, RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3B82F6).copy(alpha = 0.10f),
                            Color.White
                        )
                    )
                )
                .padding(16.dp)
        ) {

            // -------- COVER IMAGE -------- //
            AsyncImage(
                model = book.coverImageUrl,
                contentDescription = "Book Cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // -------- TITLE + BOOKMARK ROW -------- //
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = book.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { bookmarked = !bookmarked }) {
                    Icon(
                        imageVector = if (bookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = Color(0xFF2563EB)
                    )
                }
            }

            // -------- AUTHOR -------- //
            Text(
                text = "By ${book.author}",
                fontSize = 15.sp,
                color = Color(0xFF475569),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // -------- RATING -------- //
            RatingStars(book.rating)

            Spacer(modifier = Modifier.height(10.dp))

            // -------- CATEGORY TAG -------- //
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF2563EB).copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = book.category,
                    fontSize = 12.sp,
                    color = Color(0xFF1E40AF),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // -------- SHORT DESCRIPTION -------- //
            Text(
                text = book.description.take(120) + "...",
                fontSize = 14.sp,
                color = Color(0xFF334155)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // -------- DOWNLOAD BUTTON -------- //
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB)
                )
            ) {
                Icon(Icons.Default.Download, contentDescription = "Download", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Download PDF", color = Color.White)
            }
        }
    }
}


// ---------------- RATING STARS ------------------ //

@Composable
fun RatingStars(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        val fullStars = rating.toInt()
        val halfStar = rating % 1 >= 0.5

        repeat(fullStars) {
            Icon(
                Icons.Default.Star,
                contentDescription = "Star",
                tint = Color(0xFFFFB703),
                modifier = Modifier.size(18.dp)
            )
        }

        if (halfStar) {
            Icon(
                Icons.Default.StarHalf,
                contentDescription = "Half Star",
                tint = Color(0xFFFFB703),
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = "  $rating",
            fontSize = 13.sp,
            color = Color(0xFF475569),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
