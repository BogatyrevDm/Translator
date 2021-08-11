package com.example.repository

interface DataSource<T> {
    suspend fun getData(word: String): List<T>
}