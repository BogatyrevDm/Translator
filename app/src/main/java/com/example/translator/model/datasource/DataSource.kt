package com.example.translator.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): List<T>
}