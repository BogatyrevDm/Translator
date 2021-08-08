package com.example.translator.model.repository

interface Repository<T> {
    suspend fun getData(word: String): List<T>
}