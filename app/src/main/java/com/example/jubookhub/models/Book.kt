package com.example.jubookhub.models

data class Book(
    val id: String,                     // Unique book ID
    val title: String,                  // Book title
    val author: String,                 // Author name
    val publicationYear: Int,           // Year of publication

    val description: String = "",       // Short overview
    val category: String = "",          // AI / ML / Cybersecurity etc
    val language: String = "English",   // Language of the book
    val edition: String = "1st",        // Edition info

    val rating: Double = 0.0,           // Rating e.g. 4.5
    val reviewsCount: Int = 0,          // How many reviewers

    val coverImageUrl: String = "",     // Online cover image link
    val fileSizeMB: Double = 0.0,       // File size in MB
    val pdfUrl: String = "",            // Download link
    val previewUrl: String = "",        // In-app preview URL

    val tags: List<String> = emptyList(),     // Tags for filtering
    val readTimeMinutes: Int = 0,       // Estimated reading time

    val isBookmarked: Boolean = false,  // User saved or not
    val isDownloaded: Boolean = false,  // Offline availability

    val uploadDate: String = ""         // When added to database
)
