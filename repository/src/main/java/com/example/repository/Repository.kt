package com.example.repository

interface Repository<T> {
    suspend fun getData(word: String): List<T>
}