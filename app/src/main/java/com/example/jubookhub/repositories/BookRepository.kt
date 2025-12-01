package com.example.jubookhub.repositories

import com.example.jubookhub.models.Book
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val booksCollection = firestore.collection("books")

    suspend fun addBook(book: Book) {
        try {
            booksCollection.add(book).await()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getBooks(): List<Book> {
        return try {
            val snapshot = booksCollection.get().await()
            snapshot.toObjects(Book::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
